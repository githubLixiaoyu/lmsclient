package com.zxtech.ui.vo.app;

import java.util.Date;

public class TUserLearningCourse extends TUserLearningCourseKey {
    private Date starttime;

    private Date endtime;

    private String nodeid;

    private String learningstate;

    private Double learningtime;

    private String isusepaper;

    private Double examresults;

    private Date laststarttime;

    private Date lastendtime;

    private Double credits;

    private String status;

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

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }

    public String getLearningstate() {
        return learningstate;
    }

    public void setLearningstate(String learningstate) {
        this.learningstate = learningstate == null ? null : learningstate.trim();
    }

    public Double getLearningtime() {
        return learningtime;
    }

    public void setLearningtime(Double learningtime) {
        this.learningtime = learningtime;
    }

    public String getIsusepaper() {
        return isusepaper;
    }

    public void setIsusepaper(String isusepaper) {
        this.isusepaper = isusepaper == null ? null : isusepaper.trim();
    }

    public Double getExamresults() {
        return examresults;
    }

    public void setExamresults(Double examresults) {
        this.examresults = examresults;
    }

    public Date getLaststarttime() {
        return laststarttime;
    }

    public void setLaststarttime(Date laststarttime) {
        this.laststarttime = laststarttime;
    }

    public Date getLastendtime() {
        return lastendtime;
    }

    public void setLastendtime(Date lastendtime) {
        this.lastendtime = lastendtime;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}