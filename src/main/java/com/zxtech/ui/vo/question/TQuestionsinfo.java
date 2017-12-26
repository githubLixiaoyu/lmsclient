package com.zxtech.ui.vo.question;

import java.util.Date;

public class TQuestionsinfo {
    private String questionsid;

    private String typeid;

    private String difficulty;

    private String status;

    private Integer score;

    private String staffid;

    private Date createtime;

    private String auditid;

    private Date audittime;

    private String standby1;

    private String standby2;

    private String standby3;

    private String pointsid;

    private String flowstatus;

    private String optionstatus;

    private String attachstatus;
    
    private String content;
    
    private String loginUserId;
//    
//    private String auditcontent;
//    
//    private String remarks;
//    
//    private String answer;
    
    private String knowlegdePointsid;
    
    private String paperid;
    
    private String difficultydetailid;

    public String getQuestionsid() {
        return questionsid;
    }

    public void setQuestionsid(String questionsid) {
        this.questionsid = questionsid == null ? null : questionsid.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty == null ? null : difficulty.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1 == null ? null : standby1.trim();
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2 == null ? null : standby2.trim();
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3 == null ? null : standby3.trim();
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid == null ? null : pointsid.trim();
    }

    public String getFlowstatus() {
        return flowstatus;
    }

    public void setFlowstatus(String flowstatus) {
        this.flowstatus = flowstatus == null ? null : flowstatus.trim();
    }

    public String getOptionstatus() {
        return optionstatus;
    }

    public void setOptionstatus(String optionstatus) {
        this.optionstatus = optionstatus == null ? null : optionstatus.trim();
    }

    public String getAttachstatus() {
        return attachstatus;
    }

    public void setAttachstatus(String attachstatus) {
        this.attachstatus = attachstatus == null ? null : attachstatus.trim();
    }

	public String getKnowlegdePointsid() {
		return knowlegdePointsid;
	}

	public void setKnowlegdePointsid(String knowlegdePointsid) {
		this.knowlegdePointsid = knowlegdePointsid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getDifficultydetailid() {
		return difficultydetailid;
	}

	public void setDifficultydetailid(String difficultydetailid) {
		this.difficultydetailid = difficultydetailid;
	}
	
}