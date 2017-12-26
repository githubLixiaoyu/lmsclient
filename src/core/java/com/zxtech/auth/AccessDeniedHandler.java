package com.zxtech.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

import com.zxtech.support.http.HttpUtil;

public class AccessDeniedHandler implements
		org.springframework.security.web.access.AccessDeniedHandler {
	
	private Logger logger = LoggerFactory.getLogger(AccessDeniedHandler.class);
	
	private String accessDeniedUrl;
	
	public AccessDeniedHandler(){
		
	}
	
	public AccessDeniedHandler(String accessDeniedUrl){
		this.accessDeniedUrl = accessDeniedUrl;
	}
	
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		logger.debug("没有权限");
		
		if(HttpUtil.isAsynRequest(request)){
			Map<String, String> data = new HashMap<String, String>();
			data.put("flag", HttpUtil.FAIL_FLG);
			data.put("msg", "没有权限");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			HttpUtil.outJson(response, data);
		} else {
			request.getRequestDispatcher(accessDeniedUrl).forward(request, response);
	
	        String deniedMessage = accessDeniedException.getMessage();
	
	        String rp = request.getRequestURI();
	
	        request.getSession().setAttribute("ACCESS_DENIED_MSG", deniedMessage);
		}

	}
	

}
