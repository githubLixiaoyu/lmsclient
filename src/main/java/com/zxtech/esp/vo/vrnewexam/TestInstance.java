package com.zxtech.esp.vo.vrnewexam;

import java.util.Date;

public class TestInstance {
    private Long id;

    private Long version;

    private Long testTemplateId;

    private Long testTemplateMileStoneId;

    private String userId;

    private Long testInstanceRequestId;

    private Long totalTime;

    private Date endTime;

    private Date startTime;

    private Double totalScore;

    private Long testInstanceTypeId;

    private Long testCompleteOperationId;

    private Long testCompleteOperationUsedTime;

    private String userIdStr;
    
    private String[] userIdList;

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

    public Long getTestTemplateId() {
        return testTemplateId;
    }

    public void setTestTemplateId(Long testTemplateId) {
        this.testTemplateId = testTemplateId;
    }

    public Long getTestTemplateMileStoneId() {
        return testTemplateMileStoneId;
    }

    public void setTestTemplateMileStoneId(Long testTemplateMileStoneId) {
        this.testTemplateMileStoneId = testTemplateMileStoneId;
    }

    public Long getTestInstanceRequestId() {
        return testInstanceRequestId;
    }

    public void setTestInstanceRequestId(Long testInstanceRequestId) {
        this.testInstanceRequestId = testInstanceRequestId;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Long getTestInstanceTypeId() {
        return testInstanceTypeId;
    }

    public void setTestInstanceTypeId(Long testInstanceTypeId) {
        this.testInstanceTypeId = testInstanceTypeId;
    }

    public Long getTestCompleteOperationId() {
        return testCompleteOperationId;
    }

    public void setTestCompleteOperationId(Long testCompleteOperationId) {
        this.testCompleteOperationId = testCompleteOperationId;
    }

    public Long getTestCompleteOperationUsedTime() {
        return testCompleteOperationUsedTime;
    }

    public void setTestCompleteOperationUsedTime(Long testCompleteOperationUsedTime) {
        this.testCompleteOperationUsedTime = testCompleteOperationUsedTime;
    }

	public String[] getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String[] userIdList) {
		this.userIdList = userIdList;
	}

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}