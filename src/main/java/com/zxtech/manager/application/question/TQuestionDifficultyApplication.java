package com.zxtech.manager.application.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionDifficulty;

public interface TQuestionDifficultyApplication {
	//查询
	public List<Map<String, Object>> selectOptionQuestionDifficultyList(TQuestionDifficulty record) throws Exception;
}
