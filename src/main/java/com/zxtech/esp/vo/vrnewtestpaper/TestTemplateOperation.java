package com.zxtech.esp.vo.vrnewtestpaper;

public class TestTemplateOperation {
    private Long id;

    private Long version;

    private Long demoavifileId;

    private String operationDescription;

    private String operationName;

    private Long systemDialogId;

    private Long testTemplateStepId;

    private String operationIconFileName;

    private Long milestoneId;

    private Long templateId;

    private String operationKey;

    private Long operationType;

    private Long sequenceNumber;

    private Long allowMaximumTime;

    private Long standardTime;

    private Long isMultiPerson;

    private Long multiOperationId;

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

    public Long getDemoavifileId() {
        return demoavifileId;
    }

    public void setDemoavifileId(Long demoavifileId) {
        this.demoavifileId = demoavifileId;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription == null ? null : operationDescription.trim();
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public Long getSystemDialogId() {
        return systemDialogId;
    }

    public void setSystemDialogId(Long systemDialogId) {
        this.systemDialogId = systemDialogId;
    }

    public Long getTestTemplateStepId() {
        return testTemplateStepId;
    }

    public void setTestTemplateStepId(Long testTemplateStepId) {
        this.testTemplateStepId = testTemplateStepId;
    }

    public String getOperationIconFileName() {
        return operationIconFileName;
    }

    public void setOperationIconFileName(String operationIconFileName) {
        this.operationIconFileName = operationIconFileName == null ? null : operationIconFileName.trim();
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(String operationKey) {
        this.operationKey = operationKey == null ? null : operationKey.trim();
    }

    public Long getOperationType() {
        return operationType;
    }

    public void setOperationType(Long operationType) {
        this.operationType = operationType;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getAllowMaximumTime() {
        return allowMaximumTime;
    }

    public void setAllowMaximumTime(Long allowMaximumTime) {
        this.allowMaximumTime = allowMaximumTime;
    }

    public Long getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(Long standardTime) {
        this.standardTime = standardTime;
    }

    public Long getIsMultiPerson() {
        return isMultiPerson;
    }

    public void setIsMultiPerson(Long isMultiPerson) {
        this.isMultiPerson = isMultiPerson;
    }

    public Long getMultiOperationId() {
        return multiOperationId;
    }

    public void setMultiOperationId(Long multiOperationId) {
        this.multiOperationId = multiOperationId;
    }
}