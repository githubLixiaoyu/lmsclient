package com.zxtech.auth;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.zxtech.support.md5.MessageDigestUtil;

public class MessageDigestEncoder implements PasswordEncoder {
	@Override
	public String encodePassword(String password, Object salt) throws DataAccessException {
		return MessageDigestUtil.encode(password);

	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
		String pass1 = "" + encPass;
		if(pass1.equals(rawPass)){
			return true;
		} else {
	        String pass2 = encodePassword(rawPass, salt);
	
	        return pass1.equals(pass2);
		}
	}

}
