package com.zxtech.auth.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.zxtech.auth.CustomUserDetails;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.dao.app.TAppMapper;
import com.zxtech.ui.vo.mobile.MBaseVO;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	public AuthenticationSuccessHandler() {
	
	}
	
	public AuthenticationSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}
	
	@Inject
	private TAppMapper tAppMapper;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
		
		String platform = request.getParameter("platform");
		//查询移动端信息LIULEI
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", MBaseVO.STATE_SUCC);
		result.put("msg", "登录成功");

		String url = Util.readPropertiesValue("UserPhoto");
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userDetails.getUserId());
		data.put("userName", userDetails.getUsername());
		data.put("token", userDetails.getToken());
		data.put("photo_name", userDetails.getPhotoName());
		data.put("nick_name", userDetails.getNickName());
		data.put("photo_url", url + userDetails.getPhotoName());
		
		List<Map<String, Object>> apps = tAppMapper.checkNewVersion(platform);
		if(apps!=null && apps.size()>0){
			
			String apkurl = Util.readPropertiesValue("apkUrl");
			Map<String, Object> map = apps.get(0);
			
			Integer version_code = Integer.valueOf(map.get("versionCode").toString());
			Integer must_update = Integer.valueOf(map.get("mustUpdate").toString());
			String filename = String.valueOf(map.get("filename"));
			data.put("version_code", version_code);
			data.put("must_update", must_update);
			data.put("filename", filename);
			data.put("fileurl", apkurl + filename);
		}

		result.put("data", data);
		HttpUtil.outJson(response, result);
	}
}
