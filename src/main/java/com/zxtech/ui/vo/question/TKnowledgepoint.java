package com.zxtech.ui.vo.question;

public class TKnowledgepoint {
    private String point;

    private String parentpoint;

    private String pointname;

    private String categoryid;

    private String standby1;

    private String remark;

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point == null ? null : point.trim();
    }

    public String getParentpoint() {
        return parentpoint;
    }

    public void setParentpoint(String parentpoint) {
        this.parentpoint = parentpoint == null ? null : parentpoint.trim();
    }

    public String getPointname() {
        return pointname;
    }

    public void setPointname(String pointname) {
        this.pointname = pointname == null ? null : pointname.trim();
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid == null ? null : categoryid.trim();
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1 == null ? null : standby1.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}