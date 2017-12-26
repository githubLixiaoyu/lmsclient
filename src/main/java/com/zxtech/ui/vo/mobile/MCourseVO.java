package com.zxtech.ui.vo.mobile;

import java.io.Serializable;

public class MCourseVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String courseId;
	private String userId;
	private String learnLength;
	private String score;
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLearnLength() {
		return learnLength;
	}
	public void setLearnLength(String learnLength) {
		this.learnLength = learnLength;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
