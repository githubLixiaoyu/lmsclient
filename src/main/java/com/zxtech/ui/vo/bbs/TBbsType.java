package com.zxtech.ui.vo.bbs;

public class TBbsType {
    private Integer id;

    private String typeName;
    
    private String typeNameEn;

    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

	public String getTypeNameEn() {
		return typeNameEn;
	}

	public void setTypeNameEn(String typeNameEn) {
		this.typeNameEn = typeNameEn;
	}
}