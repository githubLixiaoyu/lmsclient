package com.zxtech.ui.vo.newexam;

import java.math.BigDecimal;

public class TNewexamProcedure extends TNewexamProcedureKey {
    private String typeid;

    private BigDecimal score;

    private String corrigndum;

    private BigDecimal questionsocre;

    private String problemstate;

    private String answer;
    
    private Double continuetime;

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getCorrigndum() {
        return corrigndum;
    }

    public void setCorrigndum(String corrigndum) {
        this.corrigndum = corrigndum == null ? null : corrigndum.trim();
    }

    public BigDecimal getQuestionsocre() {
        return questionsocre;
    }

    public void setQuestionsocre(BigDecimal questionsocre) {
        this.questionsocre = questionsocre;
    }

    public String getProblemstate() {
        return problemstate;
    }

    public void setProblemstate(String problemstate) {
        this.problemstate = problemstate == null ? null : problemstate.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

	public Double getContinuetime() {
		return continuetime;
	}

	public void setContinuetime(Double continuetime) {
		this.continuetime = continuetime;
	}
}