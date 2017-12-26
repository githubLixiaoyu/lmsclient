package com.zxtech.esp.vo.vrnewexam;

import java.math.BigDecimal;
import java.util.Date;

public class TestInstanceRequest {
    private Long id;

    private Long version;

    private String description;

    private String requestUserId;

    private Long testTemplateId;

    private Long testTemplateMileStoneId;

    private Date createDate;

    private Date testEndDate;

    private Date testStartDate;

    private Long isEnd;

    private Long paperScore;

    private String examType;

    private String examKind;

    private String examTarget;

    private Integer examTime;

    private BigDecimal passScore;

    private Integer passScoreStatus;

    private String imageName;

    private String mileStoneName;
    
    private String testName;
    
    private String statusTypeId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getTestEndDate() {
        return testEndDate;
    }

    public void setTestEndDate(Date testEndDate) {
        this.testEndDate = testEndDate;
    }

    public Date getTestStartDate() {
        return testStartDate;
    }

    public void setTestStartDate(Date testStartDate) {
        this.testStartDate = testStartDate;
    }

    public Long getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Long isEnd) {
        this.isEnd = isEnd;
    }

    public Long getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Long paperScore) {
        this.paperScore = paperScore;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType == null ? null : examType.trim();
    }

    public String getExamKind() {
        return examKind;
    }

    public void setExamKind(String examKind) {
        this.examKind = examKind == null ? null : examKind.trim();
    }

    public String getExamTarget() {
        return examTarget;
    }

    public void setExamTarget(String examTarget) {
        this.examTarget = examTarget == null ? null : examTarget.trim();
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public Integer getPassScoreStatus() {
        return passScoreStatus;
    }

    public void setPassScoreStatus(Integer passScoreStatus) {
        this.passScoreStatus = passScoreStatus;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

	public String getMileStoneName() {
		return mileStoneName;
	}

	public void setMileStoneName(String mileStoneName) {
		this.mileStoneName = mileStoneName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getStatusTypeId() {
		return statusTypeId;
	}

	public void setStatusTypeId(String statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	public BigDecimal getPassScore() {
		return passScore;
	}

	public void setPassScore(BigDecimal passScore) {
		this.passScore = passScore;
	}

	public String getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}

}