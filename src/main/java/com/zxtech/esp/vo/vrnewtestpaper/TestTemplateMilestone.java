package com.zxtech.esp.vo.vrnewtestpaper;

import java.util.Date;

public class TestTemplateMilestone {
	private Long id;

	private Long version;

	private String mileStoneName;

	private Long testTemplateId;

	private Long sequenceNumber;

	private Long statusTypeId;

	private Long typeId;

	private Date createDate;

	private String description;

	private String requestUserId;

	private Long allowMaximumTime;

	private Long detialInfoId;

	private Long openType;

	private Long movieId;

	private String simulatorType;

	private String urlProtocol;

	private String plateformFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getMileStoneName() {
		return mileStoneName;
	}

	public void setMileStoneName(String mileStoneName) {
		this.mileStoneName = mileStoneName;
	}

	public Long getTestTemplateId() {
		return testTemplateId;
	}

	public void setTestTemplateId(Long testTemplateId) {
		this.testTemplateId = testTemplateId;
	}

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Long getStatusTypeId() {
		return statusTypeId;
	}

	public void setStatusTypeId(Long statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}

	public Long getAllowMaximumTime() {
		return allowMaximumTime;
	}

	public void setAllowMaximumTime(Long allowMaximumTime) {
		this.allowMaximumTime = allowMaximumTime;
	}

	public Long getDetialInfoId() {
		return detialInfoId;
	}

	public void setDetialInfoId(Long detialInfoId) {
		this.detialInfoId = detialInfoId;
	}

	public Long getOpenType() {
		return openType;
	}

	public void setOpenType(Long openType) {
		this.openType = openType;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getSimulatorType() {
		return simulatorType;
	}

	public void setSimulatorType(String simulatorType) {
		this.simulatorType = simulatorType;
	}

	public String getUrlProtocol() {
		return urlProtocol;
	}

	public void setUrlProtocol(String urlProtocol) {
		this.urlProtocol = urlProtocol;
	}

	public String getPlateformFlag() {
		return plateformFlag;
	}

	public void setPlateformFlag(String plateformFlag) {
		this.plateformFlag = plateformFlag;
	}

	@Override
	public String toString() {
		return "TestTemplateMilestone [id=" + id + ", version=" + version + ", mileStoneName=" + mileStoneName
				+ ", testTemplateId=" + testTemplateId + ", sequenceNumber=" + sequenceNumber + ", statusTypeId="
				+ statusTypeId + ", typeId=" + typeId + ", createDate=" + createDate + ", description=" + description
				+ ", requestUserId=" + requestUserId + ", allowMaximumTime=" + allowMaximumTime + ", detialInfoId="
				+ detialInfoId + ", openType=" + openType + ", movieId=" + movieId + ", simulatorType=" + simulatorType
				+ ", urlProtocol=" + urlProtocol + ", plateformFlag=" + plateformFlag + "]";
	}

	
}