package com.zxtech.auth.entrypoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

import com.zxtech.support.http.HttpUtil;


public class LoginUrlAuthenticationEntryPoint extends
	org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint {
	Logger Logger = LoggerFactory.getLogger(LoginUrlAuthenticationEntryPoint.class);
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		Logger.debug("没登录");
		
		String requestURI = request.getRequestURI();
		if (!"/lmsclient/ui/tpermissionsstudentinfo/selectLoginUser.do".equals(requestURI)) {
			boolean uiflag = false;
			if(StringUtils.startsWith(requestURI, "/lmsclient/pages/ui") || StringUtils.startsWith(requestURI, "/lmsclient/ui")){
				uiflag = true;
			}
			
			if(HttpUtil.isAsynRequest(request)){
				Map<String, String> data = new HashMap<String, String>();
				data.put("flag", HttpUtil.FAIL_FLG);
				data.put("msg", "没有登录");
				if(uiflag){
					data.put("uiflag", "前台页面");
				} else {
					data.put("uiflag", "后台页面");
				}
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				HttpUtil.outJson(response, data);
			} else {	
				if(uiflag){
					setLoginFormUrl("/login.jsp");
				} else {
					setLoginFormUrl("/manager.jsp");
				}
				super.commence(request, response, authException);
			}
		}
	}

}
