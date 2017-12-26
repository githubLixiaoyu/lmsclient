package com.zxtech.manager.vo.function;

import java.util.Date;

public class TAuthFunctionRelation {
    private Integer id;

    private Integer authId;

    private Integer functionId;

    private Date createTime;

    private Date updateTime;

    private Integer removed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }
}