package com.zxtech.ui.vo.newexam;

import java.util.Date;

public class TNewexamDetails extends TNewexamDetailsKey {
    private String status;

    private Date starttime;

    private Date endtime;

    private String remainingtime;

    private String reviewstatus;

    private Double continuetime;

    private String ifappend;

    private Double appendtime;

    private String ifapplay;

    private String applaymsg;

    private String paperremark;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getRemainingtime() {
        return remainingtime;
    }

    public void setRemainingtime(String remainingtime) {
        this.remainingtime = remainingtime == null ? null : remainingtime.trim();
    }

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus == null ? null : reviewstatus.trim();
    }

    public Double getContinuetime() {
        return continuetime;
    }

    public void setContinuetime(Double continuetime) {
        this.continuetime = continuetime;
    }

    public String getIfappend() {
        return ifappend;
    }

    public void setIfappend(String ifappend) {
        this.ifappend = ifappend == null ? null : ifappend.trim();
    }

    public Double getAppendtime() {
        return appendtime;
    }

    public void setAppendtime(Double appendtime) {
        this.appendtime = appendtime;
    }

    public String getIfapplay() {
        return ifapplay;
    }

    public void setIfapplay(String ifapplay) {
        this.ifapplay = ifapplay == null ? null : ifapplay.trim();
    }

    public String getApplaymsg() {
        return applaymsg;
    }

    public void setApplaymsg(String applaymsg) {
        this.applaymsg = applaymsg == null ? null : applaymsg.trim();
    }

    public String getPaperremark() {
        return paperremark;
    }

    public void setPaperremark(String paperremark) {
        this.paperremark = paperremark == null ? null : paperremark.trim();
    }
}