package com.zxtech.ui.vo.newtestpaper;

import java.util.Date;

public class TNewTestpaperInfo {
    private String paperid;

    private String papername;

    private String categoryid;

    private Short score;

    private String purpose;

    private String creater;

    private Date createtime;

    private String auditor;

    private Date audittime;

    private String status;

    private String difficult;

    private String ifcompleted;

    private String papertype;

    private String papermode;

    private String activetimestatus;

    private Date activetime;
    
    private String forpaper;

    private String auditremark;

    private String remark;
    
    private String loginUserId;

    public String getForpaper() {
        return forpaper;
    }

    public void setForpaper(String forpaper) {
        this.forpaper = forpaper == null ? null : forpaper.trim();
    }

    public String getAuditremark() {
        return auditremark;
    }

    public void setAuditremark(String auditremark) {
        this.auditremark = auditremark == null ? null : auditremark.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid == null ? null : paperid.trim();
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername == null ? null : papername.trim();
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid == null ? null : categoryid.trim();
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult == null ? null : difficult.trim();
    }

    public String getIfcompleted() {
        return ifcompleted;
    }

    public void setIfcompleted(String ifcompleted) {
        this.ifcompleted = ifcompleted == null ? null : ifcompleted.trim();
    }

    public String getPapertype() {
        return papertype;
    }

    public void setPapertype(String papertype) {
        this.papertype = papertype == null ? null : papertype.trim();
    }

    public String getPapermode() {
        return papermode;
    }

    public void setPapermode(String papermode) {
        this.papermode = papermode == null ? null : papermode.trim();
    }

    public String getActivetimestatus() {
        return activetimestatus;
    }

    public void setActivetimestatus(String activetimestatus) {
        this.activetimestatus = activetimestatus == null ? null : activetimestatus.trim();
    }

    public Date getActivetime() {
        return activetime;
    }

    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
}