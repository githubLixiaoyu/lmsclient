package com.zxtech.ui.vo.newexam;

public class TNewexamImage {
    private String id;

    private String examid;

    private String imgpath;

    private String imgtype;

    private String imgname;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid == null ? null : examid.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}