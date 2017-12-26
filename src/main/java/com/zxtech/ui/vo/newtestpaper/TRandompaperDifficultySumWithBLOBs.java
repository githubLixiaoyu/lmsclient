package com.zxtech.ui.vo.newtestpaper;

public class TRandompaperDifficultySumWithBLOBs extends TRandompaperDifficultySum {
    private String remark;

    private String categoryid;

    private String knowledgepoint;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid == null ? null : categoryid.trim();
    }

    public String getKnowledgepoint() {
        return knowledgepoint;
    }

    public void setKnowledgepoint(String knowledgepoint) {
        this.knowledgepoint = knowledgepoint == null ? null : knowledgepoint.trim();
    }
}