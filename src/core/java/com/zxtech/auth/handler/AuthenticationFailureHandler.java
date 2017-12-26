package com.zxtech.auth.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.zxtech.support.http.HttpUtil;


public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	public AuthenticationFailureHandler() {
    }

    public AuthenticationFailureHandler(String defaultFailureUrl) {
        super(defaultFailureUrl);
    }
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		Map<String, String> data = new HashMap<String, String>();
		data.put("status", HttpUtil.FAIL_FLG);
		data.put("msg", "用户名或密码错误");
		data.put("msg_en", "Account or password error. Enter again.");
		HttpUtil.outJson(response, data);
	}
}
