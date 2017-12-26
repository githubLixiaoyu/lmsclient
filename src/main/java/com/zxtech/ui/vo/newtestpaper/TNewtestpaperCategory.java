package com.zxtech.ui.vo.newtestpaper;

public class TNewtestpaperCategory {
    private String pointsid;

    private String pointsname;

    private String pointsparentid;

    private String status;

    private String creater;

    private String standby1;

    private String standby2;

    private String standby3;

    private String remark;

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid == null ? null : pointsid.trim();
    }

    public String getPointsname() {
        return pointsname;
    }

    public void setPointsname(String pointsname) {
        this.pointsname = pointsname == null ? null : pointsname.trim();
    }

    public String getPointsparentid() {
        return pointsparentid;
    }

    public void setPointsparentid(String pointsparentid) {
        this.pointsparentid = pointsparentid == null ? null : pointsparentid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}