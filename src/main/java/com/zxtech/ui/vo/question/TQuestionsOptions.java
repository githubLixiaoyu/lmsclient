package com.zxtech.ui.vo.question;

public class TQuestionsOptions {
    private String optionsid;

    private String questionsid;

    private String standby1;

    private String standby2;

    private String standby3;

    private Integer level;

    private String optionscontent;

    public String getOptionsid() {
        return optionsid;
    }

    public void setOptionsid(String optionsid) {
        this.optionsid = optionsid == null ? null : optionsid.trim();
    }

    public String getQuestionsid() {
        return questionsid;
    }

    public void setQuestionsid(String questionsid) {
        this.questionsid = questionsid == null ? null : questionsid.trim();
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getOptionscontent() {
        return optionscontent;
    }

    public void setOptionscontent(String optionscontent) {
        this.optionscontent = optionscontent == null ? null : optionscontent.trim();
    }
}