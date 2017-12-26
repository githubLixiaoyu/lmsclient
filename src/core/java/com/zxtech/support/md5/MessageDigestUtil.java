package com.zxtech.support.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {
	public static String encode(String password){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    	
	}
}
