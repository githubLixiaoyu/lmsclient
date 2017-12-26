package com.zxtech.ui.application.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface TAppApplication {
	
	Map<String, Object> getQrcode(HttpServletRequest request) throws Exception;
}
