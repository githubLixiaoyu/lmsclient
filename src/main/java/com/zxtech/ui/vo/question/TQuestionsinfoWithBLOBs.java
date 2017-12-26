package com.zxtech.ui.vo.question;

public class TQuestionsinfoWithBLOBs extends TQuestionsinfo {
    private String content;

    private String auditcontent;

    private String remarks;

    private String answer;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAuditcontent() {
        return auditcontent;
    }

    public void setAuditcontent(String auditcontent) {
        this.auditcontent = auditcontent == null ? null : auditcontent.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}