package com.zxtech.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.codec.Base64;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zxtech.support.cache.Cache;
import com.zxtech.support.cache.ehcache.EhCacheImpl;
import com.zxtech.ui.dao.permissions.TPermissionsStudentinfoMapper;

import net.sf.ehcache.CacheManager;

/**
 * 获得用户的详细信息
 * 
 * @author sunji
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	@Inject 
	private TPermissionsStudentinfoMapper tPermissionsStudentinfoMapper;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException, DataAccessException {
		
		CustomUserDetails userDetails = null;
		if(CacheManager.getInstance().cacheExists(userId)){
			//获取存在的cache
			Cache cache = new EhCacheImpl(userId);
			//获得cache中的内容
			if(cache.isKeyInCache(userId)){
				userDetails = (CustomUserDetails) cache.get(userId);
				return userDetails;
			}
		}
		
		String password = "";
		String studentId = "";
		String nickName = "";
		String phone = "";
		String photoName = "";
		String authCode = "";
		String departid = "";
		String departname = "";
		String authId = "";
		//数据库查询登录这信-LIULEI
		List<Map<String, Object>> list = tPermissionsStudentinfoMapper.login(userId);
		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			password = String.valueOf(map.get("password"));
			studentId = String.valueOf(map.get("studentid"));
			nickName = String.valueOf(map.get("nickname"));
			phone = String.valueOf(map.get("phone"));
			photoName = String.valueOf(map.get("photoname"));
			authCode = String.valueOf(map.get("authCode"));
			departid = String.valueOf(map.get("departid"));
			departname = String.valueOf(map.get("departname"));
			authId = String.valueOf(map.get("authId"));
			
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//			if(userId.equalsIgnoreCase("admin")){
//				grantedAuthorities.add(new GrantedAuthorityImpl("super"));
//			} else {
//				grantedAuthorities.add(new GrantedAuthorityImpl("student"));
//			}
			grantedAuthorities.add(new GrantedAuthorityImpl(authCode));
			
			long now = new Date().getTime();
			String key = userId + "=" + now;
			String token = new String(Base64.encode(key.getBytes()));
			
			userDetails = new CustomUserDetails(password, userId, true, true, true, true, grantedAuthorities, studentId, nickName, phone, token, photoName, departid, departname, authId);
		}

		
		return userDetails;
	}
}
