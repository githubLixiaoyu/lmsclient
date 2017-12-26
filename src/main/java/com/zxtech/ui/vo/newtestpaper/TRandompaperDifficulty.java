package com.zxtech.ui.vo.newtestpaper;

public class TRandompaperDifficulty {
    private String id;

    private String difficultyid;

    private Double totlequestions;

    private Double titlescore;

    private String difficulty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDifficultyid() {
        return difficultyid;
    }

    public void setDifficultyid(String difficultyid) {
        this.difficultyid = difficultyid == null ? null : difficultyid.trim();
    }

    public Double getTotlequestions() {
        return totlequestions;
    }

    public void setTotlequestions(Double totlequestions) {
        this.totlequestions = totlequestions;
    }

    public Double getTitlescore() {
        return titlescore;
    }

    public void setTitlescore(Double titlescore) {
        this.titlescore = titlescore;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty == null ? null : difficulty.trim();
    }
}