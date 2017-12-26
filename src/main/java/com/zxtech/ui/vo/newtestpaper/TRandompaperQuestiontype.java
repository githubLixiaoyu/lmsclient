package com.zxtech.ui.vo.newtestpaper;

public class TRandompaperQuestiontype {
    private String display;

    private String checkstatus;

    private Double totlequestions;

    private Double titlescore;

    private String categoryidstr;
    
    private String paperid;

    private String typeid;

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid == null ? null : paperid.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    public String getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus == null ? null : checkstatus.trim();
    }

    public Double getTotlequestions() {
        return totlequestions;
    }

    public void setTotlequestions(Double totlequestions) {
        this.totlequestions = totlequestions;
    }

    public Double getTitlescore() {
        return titlescore;
    }

    public void setTitlescore(Double titlescore) {
        this.titlescore = titlescore;
    }

    public String getCategoryidstr() {
        return categoryidstr;
    }

    public void setCategoryidstr(String categoryidstr) {
        this.categoryidstr = categoryidstr == null ? null : categoryidstr.trim();
    }
}