package com.zxtech.ui.vo.newexam;

import java.math.BigDecimal;

public class TNewexamMark extends TNewexamMarkKey {
    private BigDecimal score;

    private String comments;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}