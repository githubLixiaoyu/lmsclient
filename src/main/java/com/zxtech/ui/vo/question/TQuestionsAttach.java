package com.zxtech.ui.vo.question;

import java.util.Date;

public class TQuestionsAttach {
    private String attachid;

    private String questionsid;

    private String path;

    private String type;

    private Date attachtime;

    private String standby1;

    private String standby2;

    private String standby3;

    private String name;

    public String getAttachid() {
        return attachid;
    }

    public void setAttachid(String attachid) {
        this.attachid = attachid == null ? null : attachid.trim();
    }

    public String getQuestionsid() {
        return questionsid;
    }

    public void setQuestionsid(String questionsid) {
        this.questionsid = questionsid == null ? null : questionsid.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getAttachtime() {
        return attachtime;
    }

    public void setAttachtime(Date attachtime) {
        this.attachtime = attachtime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}