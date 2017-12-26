package com.zxtech.manager.application.question.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.manager.application.question.TQuestionDifficultyApplication;
import com.zxtech.ui.dao.question.TQuestionDifficultyMapper;
import com.zxtech.ui.vo.question.TQuestionDifficulty;

@Named
public class TQuestionDifficultyApplicationImpl implements TQuestionDifficultyApplication{
	@Inject
	private TQuestionDifficultyMapper tQuestionDifficultyMapper;

	@Override
	public List<Map<String, Object>> selectOptionQuestionDifficultyList(TQuestionDifficulty record) throws Exception{
		List<Map<String, Object>> list = tQuestionDifficultyMapper.selectQuestionDifficultyList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("difficultyId"));
			String text = String.valueOf(map.get("difficultyName"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", text);
			returnList.add(m);
		}
		return returnList;
	}
}
