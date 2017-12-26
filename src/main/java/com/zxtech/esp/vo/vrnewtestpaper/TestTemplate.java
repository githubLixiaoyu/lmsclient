package com.zxtech.esp.vo.vrnewtestpaper;

public class TestTemplate {
    private Long id;

    private Long version;

    private Long orgnizationId;

    private String testName;

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

    public Long getOrgnizationId() {
        return orgnizationId;
    }

    public void setOrgnizationId(Long orgnizationId) {
        this.orgnizationId = orgnizationId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }
}