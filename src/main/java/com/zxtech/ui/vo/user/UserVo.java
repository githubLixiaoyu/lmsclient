package com.zxtech.ui.vo.user;

import java.util.Date;

public class UserVo {
	private String userId;

	private String password;

	private String userName;

	private String lockFlg;

	private Date setDate;

	private String delFlg;

	private String lastUpdateBy;

	private Date lastUpdateDate;

	private String createdBy;

	private Date creationDate;
	
	private String roleId;
	
	private String roleName;

	private String organizationId;

	private String org;

	//组织名称Id
	private String orgNameId;
	//角色
	private String roles;
	//搜索——组织区分
	private String orgPartiName;
	//搜索——组织名称
	private String cheOreg;
	// 组织名称
	private String cheOregId;
	
	private String divisionValue;
	
	private String divisionId;
	
	private String saveOrgNameId;
	
	private String saveOrgName;

	private String searchRoleName;
	//大区Id
	private String upperOrganizationId;
	//保存——大区
	private String dqId;
	//修改密码时用到的
	private String mianUserId;
	private String mianPassword;
	
	public String getMianUserId() {
		return mianUserId;
	}

	public void setMianUserId(String mianUserId) {
		this.mianUserId = mianUserId;
	}

	public String getMianPassword() {
		return mianPassword;
	}

	public void setMianPassword(String mianPassword) {
		this.mianPassword = mianPassword;
	}

	public String getDqId() {
		return dqId;
	}

	public void setDqId(String dqId) {
		this.dqId = dqId;
	}

	public String getOrgPartiName() {
		return orgPartiName;
	}

	public void setOrgPartiName(String orgPartiName) {
		this.orgPartiName = orgPartiName;
	}

	public String getSearchRoleName() {
		return searchRoleName;
	}

	public void setSearchRoleName(String searchRoleName) {
		this.searchRoleName = searchRoleName;
	}

	public String getUpperOrganizationId() {
		return upperOrganizationId;
	}

	public void setUpperOrganizationId(String upperOrganizationId) {
		this.upperOrganizationId = upperOrganizationId;
	}

	public String getSaveOrgNameId() {
		return saveOrgNameId;
	}

	public void setSaveOrgNameId(String saveOrgNameId) {
		this.saveOrgNameId = saveOrgNameId;
	}

	public String getSaveOrgName() {
		return saveOrgName;
	}

	public void setSaveOrgName(String saveOrgName) {
		this.saveOrgName = saveOrgName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionValue() {
		return divisionValue;
	}

	public void setDivisionValue(String divisionValue) {
		this.divisionValue = divisionValue;
	}

	public String getCheOreg() {
		return cheOreg;
	}

	public void setCheOreg(String cheOreg) {
		this.cheOreg = cheOreg;
	}

	public String getCheOregId() {
		return cheOregId;
	}

	public void setCheOregId(String cheOregId) {
		this.cheOregId = cheOregId;
	}

	public String getOrgNameId() {
		return orgNameId;
	}

	public void setOrgNameId(String orgNameId) {
		this.orgNameId = orgNameId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getLockFlg() {
		return lockFlg;
	}

	public void setLockFlg(String lockFlg) {
		this.lockFlg = lockFlg == null ? null : lockFlg.trim();
	}

	public Date getSetDate() {
		return setDate;
	}

	public void setSetDate(Date setDate) {
		this.setDate = setDate;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg == null ? null : delFlg.trim();
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
