package com.zxtech.ui.vo.course;

public class TCourseImage {
    private String id;

    private String courseid;

    private String imgpath;

    private String imgtype;

    private String imgname;

    private String imgmobilename;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath == null ? null : imgpath.trim();
    }

    public String getImgtype() {
        return imgtype;
    }

    public void setImgtype(String imgtype) {
        this.imgtype = imgtype == null ? null : imgtype.trim();
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname == null ? null : imgname.trim();
    }

    public String getImgmobilename() {
        return imgmobilename;
    }

    public void setImgmobilename(String imgmobilename) {
        this.imgmobilename = imgmobilename == null ? null : imgmobilename.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}