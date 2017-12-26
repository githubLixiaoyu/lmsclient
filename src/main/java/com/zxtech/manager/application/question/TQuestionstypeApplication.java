package com.zxtech.manager.application.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionstype;

public interface TQuestionstypeApplication {
	//查询
	public List<Map<String, Object>> selectOptionQuestionstypeList(TQuestionstype record) throws Exception;
}
