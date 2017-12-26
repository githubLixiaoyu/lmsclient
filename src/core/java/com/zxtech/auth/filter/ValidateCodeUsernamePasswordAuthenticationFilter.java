package com.zxtech.auth.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.codec.Base64;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	Logger Logger = LoggerFactory.getLogger(ValidateCodeUsernamePasswordAuthenticationFilter.class);
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		//加入保存token过期判断
		return super.attemptAuthentication(request, response);
	}
	
//	@Override
//	protected String obtainPassword(HttpServletRequest request) {
//		String[] result = decodeToken(request);
//		if(null != result){
//			return result[1];
//		}
//		
//		return super.obtainPassword(request);
//	}
//	@Override
//	protected String obtainUsername(HttpServletRequest request) {
//		String[] result = decodeToken(request);
//		if(null != result){
//			return result[0];
//		}
//      
//		return super.obtainUsername(request);
//	}
	
	/**
	 * 如果保存登录的情况，传过的用户名是token  用：密=时间
	 * @param request
	 * @return 为空代表不是token
	 */
	private String[] decodeToken(HttpServletRequest request){
		String token = request.getParameter(getUsernameParameter());
		try {
			byte[] base64Token = token.getBytes("UTF-8");
			token = new String(Base64.decode(base64Token), "UTF-8");
			
			int delim = token.indexOf(":");
			int timeDelim = token.indexOf("=");

            if (delim != -1 && timeDelim != -1) {
            	String[] result = new String[2];
            	result[0] = token.substring(0, delim);
            	result[1] = token.substring(delim+1,timeDelim);
            	return result;
            }
		} catch (UnsupportedEncodingException e) {
			logger.debug("不是token");
			e.printStackTrace();
		}
		
		return null;
	}

}
