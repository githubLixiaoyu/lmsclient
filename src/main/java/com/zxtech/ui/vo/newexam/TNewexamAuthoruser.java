package com.zxtech.ui.vo.newexam;

import java.util.Date;

public class TNewexamAuthoruser {
	private String userid;

	private String examid;
	    
    private Date authortime;
    
    private String loginUserId;
    
    private String name;
    
    private String departid;
    
    private String logincode;
    
    private String nickname;
    
    private String departname;
    
    private String[] userIdList;
    
    public Date getAuthortime() {
        return authortime;
    }

    public void setAuthortime(Date authortime) {
        this.authortime = authortime;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartid() {
		return departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String[] getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String[] userIdList) {
		this.userIdList = userIdList;
	}
}