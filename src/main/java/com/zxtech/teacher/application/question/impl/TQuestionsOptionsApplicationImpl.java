package com.zxtech.teacher.application.question.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.teacher.application.question.TQuestionsOptionsApplication;
import com.zxtech.ui.dao.question.TQuestionsOptionsMapper;
import com.zxtech.ui.vo.question.TQuestionsOptions;

@Named
public class TQuestionsOptionsApplicationImpl implements TQuestionsOptionsApplication{
	@Inject
	private TQuestionsOptionsMapper tQuestionsOptionsMapper;

	@Override
	public List<Map<String, Object>> selectQuestionsOptionsList(TQuestionsOptions record) throws Exception{
		return tQuestionsOptionsMapper.selectQuestionsOptionsList(record);
	}
		
}
