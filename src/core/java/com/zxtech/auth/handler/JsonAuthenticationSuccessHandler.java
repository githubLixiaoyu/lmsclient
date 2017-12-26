package com.zxtech.auth.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.zxtech.auth.CustomUserDetails;
import com.zxtech.support.http.HttpUtil;

public class JsonAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger Logger = LoggerFactory.getLogger(JsonAuthenticationSuccessHandler.class);
	
	public JsonAuthenticationSuccessHandler() {

	}

	public JsonAuthenticationSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Logger.debug("登录成功");

		if (HttpUtil.isAsynRequest(request)) {
			
	

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", HttpUtil.SUCCESS_FLG);
			result.put("msg", "登录成功");

			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userDetails.getUserId());
			data.put("userName", userDetails.getUsername());
			data.put("token", userDetails.getToken());

			result.put("data", data);
			HttpUtil.outJson(response, result);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
