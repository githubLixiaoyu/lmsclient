package com.zxtech.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpSessionRequestCache extends org.springframework.security.web.savedrequest.HttpSessionRequestCache {
	private boolean useSavedRequest;
	@Override
	public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
		 if (useSavedRequest) {
			 super.saveRequest(request, response);
		 }
		
	}
	public boolean isUseSavedRequest() {
		return useSavedRequest;
	}
	public void setUseSavedRequest(boolean useSavedRequest) {
		this.useSavedRequest = useSavedRequest;
	}

}
