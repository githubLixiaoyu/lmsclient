package com.zxtech.ui.vo.app;

public class TCourseInfoWithBLOBs extends TCourseInfo {
    private String auditmarks;

    private String remarks;

    private String releasemarks;

    private String trustormarks;

    private String addauthorizeremarks;

    public String getAuditmarks() {
        return auditmarks;
    }

    public void setAuditmarks(String auditmarks) {
        this.auditmarks = auditmarks == null ? null : auditmarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getReleasemarks() {
        return releasemarks;
    }

    public void setReleasemarks(String releasemarks) {
        this.releasemarks = releasemarks == null ? null : releasemarks.trim();
    }

    public String getTrustormarks() {
        return trustormarks;
    }

    public void setTrustormarks(String trustormarks) {
        this.trustormarks = trustormarks == null ? null : trustormarks.trim();
    }

    public String getAddauthorizeremarks() {
        return addauthorizeremarks;
    }

    public void setAddauthorizeremarks(String addauthorizeremarks) {
        this.addauthorizeremarks = addauthorizeremarks == null ? null : addauthorizeremarks.trim();
    }
}