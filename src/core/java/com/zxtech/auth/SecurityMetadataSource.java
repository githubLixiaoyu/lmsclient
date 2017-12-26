package com.zxtech.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.zxtech.support.cache.Cache;
import com.zxtech.support.spring.ApplicationContextHelper;

/**
 * 获得请求所拥有的权限
 * 
 * @author sunji
 *
 */
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

//	@Inject
//	private RoleApplication roleApplication;
	private Cache resourceCache;

	/**
	 * 获得缓存对象
	 * 
	 * @return
	 */
	 private Cache getResourceCache(){
		 if(null == resourceCache){
			 resourceCache = ApplicationContextHelper.getBean("allResourceAndRoles");
		 }
		 return resourceCache;
	 }
	/**
	 * 加载请求url与role对应关系
	 */
	private void loadResources() {
//		Map<String, List<String>> allResourceAndRoles = null;
//		try {
//			allResourceAndRoles = roleApplication.selectByRoleAtFun();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		return allResourceAndRoles;
//		getResourceCache().put("allResourceAndRoles", allResourceAndRoles);
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

//		 if(!getResourceCache().isKeyInCache("allResourceAndRoles")){
//			 loadResources();
//		 }
//
//		 @SuppressWarnings("unchecked")
//		 Map<String, List<String>> allResourceAndRoles = (Map<String,List<String>>) getResourceCache().get("allResourceAndRoles");

		 Map<String, List<String>> allResourceAndRoles = new HashMap<>();
		// TODO 测试数据
		// List<String> roleNames = new ArrayList<>();
		// roleNames.add("admin");
		// allResourceAndRoles.put("aaa", roleNames);
//		// allResourceAndRoles.put("button.jsp", roleNames);
//		Map<String, List<String>> allResourceAndRoles = loadResources();

		String url = ((FilterInvocation) object).getRequestUrl();
		int position = url.indexOf("?");
		if (-1 != position) {
			url = url.substring(0, position);
		}

		url = url.substring(url.indexOf("/") + 1);
		
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		
		if(StringUtils.startsWith(url, "pages/manager/test.jsp")){
			configAttributes.add(new SecurityConfig("super"));
			configAttributes.add(new SecurityConfig("teacher"));
			configAttributes.add(new SecurityConfig("admin"));
		} else if(StringUtils.startsWith(url, "auth/common")){
			configAttributes.add(new SecurityConfig("super"));
			configAttributes.add(new SecurityConfig("teacher"));
			configAttributes.add(new SecurityConfig("student"));
			configAttributes.add(new SecurityConfig("admin"));
		} else if(StringUtils.startsWith(url, "pages/manager") || StringUtils.startsWith(url, "manager")){
			configAttributes.add(new SecurityConfig("super"));
			configAttributes.add(new SecurityConfig("admin"));
		} else if(StringUtils.startsWith(url, "pages/teacher") || StringUtils.startsWith(url, "teacher")){
			configAttributes.add(new SecurityConfig("teacher"));
		} else {
			configAttributes.add(new SecurityConfig("student"));
		}
		return configAttributes;
		// 获得资源对应的权限
//		List<String> roles = allResourceAndRoles.get(url);
//		if (null != roles) {
//			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
//			for (String role : roles) {
//				ConfigAttribute configAttribute = new SecurityConfig(role);
//				configAttributes.add(configAttribute);
//			}
//			return configAttributes;
//		} else {
//			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
//			return configAttributes;
//		}
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
