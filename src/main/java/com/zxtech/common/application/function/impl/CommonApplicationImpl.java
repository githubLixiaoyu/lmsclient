package com.zxtech.common.application.function.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.common.application.function.CommonApplication;
import com.zxtech.esp.dao.vrnewexam.TestInstanceRequestMapper;
import com.zxtech.manager.dao.function.TFunctionMapper;
import com.zxtech.support.util.Util;
import com.zxtech.ui.dao.newtestpaper.TNewtestpaperQuestionMapper;
import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;

@Named
public class CommonApplicationImpl implements CommonApplication {
	
	@Inject
	private TFunctionMapper tFunctionMapper;
	@Inject
	private TNewtestpaperQuestionMapper tNewtestpaperQuestionMapper;
	@Inject
	private TestInstanceRequestMapper testInstanceRequestMapper;
	
	@Override
	public List<Map<String, Object>> getFunctionMenu() throws Exception{
		String authCode = AuthDetailUtil.getAuth();
		String parentIdFlag = "";
		List<Map<String, Object>> list = tFunctionMapper.getFunctionMenu(authCode);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapSub = null;
		List<Map<String, Object>> listSub = null;
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String parentId = String.valueOf(m.get("parentId"));
				if (!parentIdFlag.equals(parentId)) {
					mapSub = new HashMap<String, Object>();
					returnList.add(mapSub);
					mapSub.put("functionName", m.get("parentFunctionName"));
					mapSub.put("functionNameEn", m.get("parentFunctionNameEn"));
					listSub = new ArrayList<Map<String, Object>>();
					mapSub.put("data", listSub);
				}
				Map<String, Object> mapDetail = new HashMap<String, Object>();
				mapDetail.put("functionName", m.get("functionName"));
				mapDetail.put("functionNameEn", m.get("functionNameEn"));
				mapDetail.put("resourceFile", m.get("resourceFile"));
				listSub.add(mapDetail);
				
				parentIdFlag = parentId;
			}
		}
		
		return returnList;
	}

	@Override
	public List<Map<String, Object>> getPaperInfoById(TNewTestpaperInfo tNewTestpaperInfo) throws Exception{
		List<Map<String, Object>> list = tNewtestpaperQuestionMapper.getPaperInfoById(tNewTestpaperInfo);
		List<Map<String, Object>> questionList = new ArrayList<Map<String, Object>>();
		Map<String, Object> questionMap = null;
		List<Map<String, Object>> optionList = null;
		Map<String, Object> optionMap = null;
		String questionIdSub = "";
		int questionNum = 1;
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String questionId = String.valueOf(m.get("questionid"));
				if (!questionIdSub.equals(questionId)) {
					questionMap = new HashMap<String, Object>();
					questionMap.put("typeId", m.get("typeid"));
					questionMap.put("typeName", m.get("name"));
					questionMap.put("questionId", m.get("questionid"));
					questionMap.put("questionNum", questionNum);
					questionMap.put("questionScore", m.get("score"));
					questionMap.put("questionContent", m.get("content"));
					optionList = new ArrayList<Map<String, Object>>();
					questionMap.put("data", optionList);
					questionList.add(questionMap);
					questionNum ++;
				}
				
				if ("T00000000000004".equals(String.valueOf(m.get("typeid")))) {
					optionMap = new HashMap<String, Object>();
					optionMap.put("optionId", "Y");
					optionMap.put("optionKey", "A");
					optionMap.put("optionContent", "正确");
					optionList.add(optionMap);
					optionMap = new HashMap<String, Object>();
					optionMap.put("optionId", "N");
					optionMap.put("optionKey", "B");
					optionMap.put("optionContent", "错误");
					optionList.add(optionMap);
				} else {
					optionMap = new HashMap<String, Object>();
					String optionKey = Util.getOptingKey(Integer.valueOf(String.valueOf(m.get("level"))).intValue());
					optionMap.put("optionId", m.get("optionsId"));
					optionMap.put("optionKey", optionKey);
					optionMap.put("optionContent", m.get("optionsContent"));
					optionList.add(optionMap);
				}
				
				questionIdSub = questionId;
			}
		}
		
		return questionList;
	}

	@Override
	public List<Map<String, Object>> selectPaperOptionsByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		List<Map<String, Object>> list = tNewtestpaperQuestionMapper.selectPaperOptionsByFilter(tNewExamInfo);
		List<Map<String, Object>> questionList = new ArrayList<Map<String, Object>>();
		Map<String, Object> questionMap = null;
		List<Map<String, Object>> optionList = null;
		Map<String, Object> optionMap = null;
		String questionIdSub = "";
		String questionAnswer = "";
		int questionNum = 1;
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String questionId = String.valueOf(m.get("questionid"));
				if (!questionIdSub.equals(questionId)) {
					questionMap = new HashMap<String, Object>();
					questionMap.put("typeId", m.get("typeid"));
					questionMap.put("typeName", m.get("name"));
					questionMap.put("questionId", m.get("questionid"));
					questionMap.put("questionNum", questionNum);
					questionMap.put("questionScore", m.get("score"));
					questionMap.put("questionContent", m.get("content"));
					questionMap.put("answer", m.get("answer"));
					questionMap.put("remarks", m.get("remarks"));
					questionMap.put("userAnswer", m.get("userAnswer"));
					questionMap.put("problemState", m.get("problemState"));
					optionList = new ArrayList<Map<String, Object>>();
					questionMap.put("data", optionList);
					questionList.add(questionMap);
					questionNum ++;
					questionAnswer = String.valueOf(m.get("answer"));
					questionMap.put("showAnswer", "");
				}
				
				if ("T00000000000004".equals(String.valueOf(m.get("typeid")))) {
					optionMap = new HashMap<String, Object>();
					optionMap.put("optionId", "Y");
					optionMap.put("optionKey", "A");
					optionMap.put("optionContent", "正确");
					optionList.add(optionMap);
					optionMap = new HashMap<String, Object>();
					optionMap.put("optionId", "N");
					optionMap.put("optionKey", "B");
					optionMap.put("optionContent", "错误");
					optionList.add(optionMap);
					if ("Y".equals(String.valueOf(m.get("answer")))) {
						questionMap.put("showAnswer", "A");
					} else {
						questionMap.put("showAnswer", "B");
					}
				} else {
					optionMap = new HashMap<String, Object>();
					String optionKey = Util.getOptingKey(Integer.valueOf(String.valueOf(m.get("level"))).intValue());
					optionMap.put("optionId", m.get("optionsId"));
					optionMap.put("optionKey", optionKey);
					optionMap.put("optionContent", m.get("optionsContent"));
					optionList.add(optionMap);
					String optionsId = String.valueOf(m.get("optionsId"));
					if (questionAnswer.indexOf(optionsId) > -1) {
						if (!"".equals(String.valueOf(questionMap.get("showAnswer")))) {
							questionMap.put("showAnswer", questionMap.get("showAnswer") + "," + optionKey);
						} else {
							questionMap.put("showAnswer", optionKey);
						}
					}
				}
				
				questionIdSub = questionId;
			}
		}
		
		return questionList;
	}

	@Override
	public Map<String, Object> selectPaperAnswersByFilter(TNewExamInfo tNewExamInfo) throws Exception{

		List<Map<String, Object>> list = tNewtestpaperQuestionMapper.selectPaperAnswersByFilter(tNewExamInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> answerMap = null;
		String questionIdSub = "";
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String questionId = String.valueOf(m.get("questionid"));
				if (!questionIdSub.equals(questionId)) {
					answerMap = new HashMap<String, Object>();
					map.put(questionId, answerMap);
					answerMap.put("remarks", m.get("remarks"));
					answerMap.put("answer", m.get("answer"));
					answerMap.put("showAnswer", Util.getOptingKey(Integer.valueOf(String.valueOf(m.get("level"))).intValue()));
				} else {
					answerMap.put("showAnswer", answerMap.get("showAnswer") + "," + Util.getOptingKey(Integer.valueOf(String.valueOf(m.get("level"))).intValue()));
				}
				questionIdSub = questionId;
			}
		}
		
		return map;
	}
	@Override
	public List<Map<String, Object>> selectVrAnswersByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		return testInstanceRequestMapper.selectVrAnswersByFilter(tNewExamInfo);
	}
}
