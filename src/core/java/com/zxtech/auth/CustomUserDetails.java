package com.zxtech.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息实体类
 * @author sunji
 *
 */
public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -289672576676553497L;
	private Collection<GrantedAuthority> authorities;
	private String userId;
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private String nickName;
	private String phone;
	private String token;
	private String photoName;
	private String departid;
	private String departname;
	private String authId;
	
	public CustomUserDetails(){
		
	}
	
	public CustomUserDetails(String password, String username, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled,Collection<GrantedAuthority> authorities,String userId,String nickName,String phone,String token,String photoName, String departid, String departname, String authId) {
		this.userId = userId;
		this.authorities = authorities;
		this.password = password;
		this.username = username;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.nickName = nickName;
		this.phone = phone;
		this.token = token;
		this.photoName = photoName;
		this.departid = departid;
		this.departname = departname;
		this.authId = authId;
		
	}

	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getDepartid() {
		return departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

}
