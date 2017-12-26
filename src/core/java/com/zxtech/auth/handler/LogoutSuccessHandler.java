package com.zxtech.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String flag = request.getParameter("flag");
		if(StringUtils.equals(flag, "ui")){
			super.setDefaultTargetUrl("/login.jsp");
		} else {
			super.setDefaultTargetUrl("/manager.jsp");
		}
		
		super.onLogoutSuccess(request, response, authentication);
	}

}
