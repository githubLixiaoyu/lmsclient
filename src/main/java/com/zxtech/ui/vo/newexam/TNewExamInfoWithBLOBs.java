package com.zxtech.ui.vo.newexam;

public class TNewExamInfoWithBLOBs extends TNewExamInfo {
    private String remark;

    private String auditremark;

    private String continuereason;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAuditremark() {
        return auditremark;
    }

    public void setAuditremark(String auditremark) {
        this.auditremark = auditremark == null ? null : auditremark.trim();
    }

    public String getContinuereason() {
        return continuereason;
    }

    public void setContinuereason(String continuereason) {
        this.continuereason = continuereason == null ? null : continuereason.trim();
    }
}