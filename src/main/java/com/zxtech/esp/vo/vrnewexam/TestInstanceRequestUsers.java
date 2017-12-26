package com.zxtech.esp.vo.vrnewexam;

public class TestInstanceRequestUsers {
    private Long id;

    private Long version;

    private Long testInstanceRequestId;

    private String userId;

    private Long userTestStateTypeId;

    private String name;
    
    private String departid;
    
    private String logincode;
    
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getTestInstanceRequestId() {
        return testInstanceRequestId;
    }

    public void setTestInstanceRequestId(Long testInstanceRequestId) {
        this.testInstanceRequestId = testInstanceRequestId;
    }

    public Long getUserTestStateTypeId() {
        return userTestStateTypeId;
    }

    public void setUserTestStateTypeId(Long userTestStateTypeId) {
        this.userTestStateTypeId = userTestStateTypeId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}