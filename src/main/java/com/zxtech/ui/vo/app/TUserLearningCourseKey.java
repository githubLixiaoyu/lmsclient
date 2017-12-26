package com.zxtech.ui.vo.app;

public class TUserLearningCourseKey {
    private String courseid;

    private String userid;

    private Integer learningnumber;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getLearningnumber() {
        return learningnumber;
    }

    public void setLearningnumber(Integer learningnumber) {
        this.learningnumber = learningnumber;
    }
}