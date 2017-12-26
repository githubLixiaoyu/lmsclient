package com.zxtech.teacher.vo.question;

import java.util.List;

import com.zxtech.ui.vo.question.TQuestionsOptions;
import com.zxtech.ui.vo.question.TQuestionsinfoWithBLOBs;

public class QuestionInfoVo {

	private TQuestionsinfoWithBLOBs questioninfo;
	
	private List<TQuestionsOptions> questionOptionsList;

	public TQuestionsinfoWithBLOBs getQuestioninfo() {
		return questioninfo;
	}

	public void setQuestioninfo(TQuestionsinfoWithBLOBs questioninfo) {
		this.questioninfo = questioninfo;
	}

	public List<TQuestionsOptions> getQuestionOptionsList() {
		return questionOptionsList;
	}

	public void setQuestionOptionsList(List<TQuestionsOptions> questionOptionsList) {
		this.questionOptionsList = questionOptionsList;
	}

}
