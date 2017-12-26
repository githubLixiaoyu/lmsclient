package com.zxtech.ui.vo.newtestpaper;

public class TRandompaperDifficultySum {
    private String id;

    private String paperid;

    private String typeid;

    private String addtype;
    
    private String categoryid;
    
    private String knowledgepoint;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    public String getAddtype() {
        return addtype;
    }

    public void setAddtype(String addtype) {
        this.addtype = addtype == null ? null : addtype.trim();
    }

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getKnowledgepoint() {
		return knowledgepoint;
	}

	public void setKnowledgepoint(String knowledgepoint) {
		this.knowledgepoint = knowledgepoint;
	}
}