package com.zxtech.auth.util;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.zxtech.auth.CustomUserDetails;

public class AuthDetailUtil {
	public static String getLoginName(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getUsername();
		} 
		return "";
	}
	
	public static CustomUserDetails getLoginInfo(){
		CustomUserDetails userDetails = null;
		try {
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return userDetails;
	}
	
	public static String getLoginUserId(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getUserId();
		}
		return "";
	} 
	
	public static String getNickName(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getNickName();
		}
		return "";
	} 
	
	public static String getPhotoName(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getPhotoName();
		}
		return "";
	} 
	
	public static String getDepartid(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getDepartid();
		}
		return "";
	} 
	
	public static String getDepartname(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getDepartname();
		}
		return "";
	} 
	
	public static String getAuthId(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			return userDetails.getAuthId();
		}
		return "";
	} 
	
	public static String getAuth(){
		CustomUserDetails userDetails = getLoginInfo();
		if(null != userDetails){
			Collection<GrantedAuthority> collection = userDetails.getAuthorities();
			Object[] o = collection.toArray();
			return String.valueOf(o[0]);
		}
		return "";
	} 
	
	public static void resetNickName(String newNickName){
		//获取当前凭证
		CustomUserDetails currentuser = getLoginInfo();
		Authentication currentauth = SecurityContextHolder.getContext().getAuthentication();
		
		//设置新凭证
		CustomUserDetails newuser = new CustomUserDetails(currentuser.getPassword(),currentuser.getUsername(),currentuser.isAccountNonExpired(), currentuser.isAccountNonLocked(), currentuser.isCredentialsNonExpired(), currentuser.isEnabled(), currentuser.getAuthorities(),currentuser.getUserId(),newNickName,currentuser.getPhone(),currentuser.getToken(),currentuser.getPhotoName(), currentuser.getDepartid(), currentuser.getDepartname(), currentuser.getAuthId());
		UsernamePasswordAuthenticationToken newauth = new UsernamePasswordAuthenticationToken(newuser,newuser.getPassword(),newuser.getAuthorities());
		newauth.setDetails(currentauth.getDetails());
		//修改凭证
		SecurityContextHolder.getContext().setAuthentication(newauth);
	}
	
	public static void resetPhotoName(String newPhotoName){
		//获取当前凭证
		CustomUserDetails currentuser = getLoginInfo();
		Authentication currentauth = SecurityContextHolder.getContext().getAuthentication();
		
		//设置新凭证
		CustomUserDetails newuser = new CustomUserDetails(currentuser.getPassword(),currentuser.getUsername(),currentuser.isAccountNonExpired(), currentuser.isAccountNonLocked(), currentuser.isCredentialsNonExpired(), currentuser.isEnabled(), currentuser.getAuthorities(),currentuser.getUserId(),currentuser.getNickName(),currentuser.getPhone(),currentuser.getToken(),newPhotoName, currentuser.getDepartid(), currentuser.getDepartname(), currentuser.getAuthId());
		UsernamePasswordAuthenticationToken newauth = new UsernamePasswordAuthenticationToken(newuser,newuser.getPassword(),newuser.getAuthorities());
		newauth.setDetails(currentauth.getDetails());
		//修改凭证
		SecurityContextHolder.getContext().setAuthentication(newauth);
	}
	
	public static void resetPassword(String newPassword){
		//获取当前凭证
		CustomUserDetails currentuser = getLoginInfo();
		Authentication currentauth = SecurityContextHolder.getContext().getAuthentication();
		
		//设置新凭证
		CustomUserDetails newuser = new CustomUserDetails(newPassword,currentuser.getUsername(),currentuser.isAccountNonExpired(), currentuser.isAccountNonLocked(), currentuser.isCredentialsNonExpired(), currentuser.isEnabled(), currentuser.getAuthorities(),currentuser.getUserId(),currentuser.getNickName(),currentuser.getPhone(),currentuser.getToken(),currentuser.getPhotoName(), currentuser.getDepartid(), currentuser.getDepartname(), currentuser.getAuthId());
		UsernamePasswordAuthenticationToken newauth = new UsernamePasswordAuthenticationToken(newuser,newPassword,newuser.getAuthorities());
		newauth.setDetails(currentauth.getDetails());
		//修改凭证
		SecurityContextHolder.getContext().setAuthentication(newauth);
	}
}
