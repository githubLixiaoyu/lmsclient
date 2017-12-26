package com.zxtech.manager.application.question.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.manager.application.question.TQuestionstypeApplication;
import com.zxtech.ui.dao.question.TQuestionstypeMapper;
import com.zxtech.ui.vo.question.TQuestionstype;

@Named
public class TQuestionstypeApplicationImpl implements TQuestionstypeApplication{
	@Inject
	private TQuestionstypeMapper tQuestionstypeMapper;

	@Override
	public List<Map<String, Object>> selectOptionQuestionstypeList(TQuestionstype record) throws Exception{
		List<Map<String, Object>> list = tQuestionstypeMapper.selectQuestionstypeList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("typeid"));
			String text = String.valueOf(map.get("name"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", text);
			returnList.add(m);
		}
		return returnList;
	}
}
