package com.zxtech.ui.vo.mobile;

import java.io.Serializable;

public class MUserVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String staffid;
	private String staffname;
	private String logincode;
	private String password;
	private String sex;
	private Long age;
	private String status; 
	private String phone; 
	
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getLogincode() {
		return logincode;
	}
	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
