package com.zxtech.support.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class SecurityMD5 {
	private static Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
	
	public static String encode(String password, String salt){
		return md5PasswordEncoder.encodePassword(password, salt);
	}
	/**
	 * 
	 * @param encPass 加密后的值
	 * @param rawPass 
	 * @param salt
	 * @return
	 */
	public static boolean isPasswordValid(String encPass,String rawPass,String salt){
		return md5PasswordEncoder.isPasswordValid(encPass, rawPass, salt);
	}
	
    public static String md5s(String plainText) throws NoSuchAlgorithmException{
    	MessageDigest md=MessageDigest.getInstance("MD5");
    	md.update(plainText.getBytes());
    	byte b[]=md.digest();
    	int i;
	   	StringBuffer buf=new StringBuffer();
	   	for(int j=0;j<b.length;j++){
	   		i=b[j];
	   		if(i<0)
	   			i+=256;
	   		if(i<16)
	   			buf.append("0");
	   		buf.append(Integer.toHexString(i));
	   		
	   	}
   	
	   	return buf.toString();
    }
}
