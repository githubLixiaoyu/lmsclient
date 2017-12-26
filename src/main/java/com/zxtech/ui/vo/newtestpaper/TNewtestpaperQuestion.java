package com.zxtech.ui.vo.newtestpaper;

public class TNewtestpaperQuestion extends TNewtestpaperQuestionKey {
    private Double score;

    private String dispaly;
    
    private String content;
    
    private String difficulty;
    
    private String pointsid;
    
    private String difficultyname;
    
    private String categoryname;
    
    private String difficultydetailid;
    
    private Integer randomNum;
    
    private String loginUserId;
    
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDispaly() {
        return dispaly;
    }

    public void setDispaly(String dispaly) {
        this.dispaly = dispaly == null ? null : dispaly.trim();
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getPointsid() {
		return pointsid;
	}

	public void setPointsid(String pointsid) {
		this.pointsid = pointsid;
	}

	public String getDifficultyname() {
		return difficultyname;
	}

	public void setDifficultyname(String difficultyname) {
		this.difficultyname = difficultyname;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getDifficultydetailid() {
		return difficultydetailid;
	}

	public void setDifficultydetailid(String difficultydetailid) {
		this.difficultydetailid = difficultydetailid;
	}

	public Integer getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(Integer randomNum) {
		this.randomNum = randomNum;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
}