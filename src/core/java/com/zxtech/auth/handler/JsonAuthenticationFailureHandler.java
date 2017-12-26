package com.zxtech.auth.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.zxtech.support.http.HttpUtil;

public class JsonAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	Logger logger = LoggerFactory.getLogger(JsonAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.debug("登录失败");

		if (HttpUtil.isAsynRequest(request)) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("status", HttpUtil.FAIL_FLG);
			data.put("msg", "用户名或密码错误");
			data.put("msg_en", "Account or password error. Enter again.");
			HttpUtil.outJson(response, data);
		} else {

			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
