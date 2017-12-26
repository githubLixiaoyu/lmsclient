package com.zxtech.ui.vo.app;

import java.util.Date;

public class TCourseType {
    private Integer id;

    private String coursetypename;

    private Integer parentid;

    private Integer sort;

    private String imagename;
    
    private String imagemobilename;

    private Integer removed;

    private Date createtime;

    private Date updatetime;
    
    private String parentname;
    
    private String remarks;
    
    private String coursetypenameEn;
    
    private String languageflag;
    
    private String userId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursetypename() {
        return coursetypename;
    }

    public void setCoursetypename(String coursetypename) {
        this.coursetypename = coursetypename == null ? null : coursetypename.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename == null ? null : imagename.trim();
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public String getImagemobilename() {
		return imagemobilename;
	}

	public void setImagemobilename(String imagemobilename) {
		this.imagemobilename = imagemobilename;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCoursetypenameEn() {
		return coursetypenameEn;
	}

	public void setCoursetypenameEn(String coursetypenameEn) {
		this.coursetypenameEn = coursetypenameEn;
	}

	public String getLanguageflag() {
		return languageflag;
	}

	public void setLanguageflag(String languageflag) {
		this.languageflag = languageflag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}