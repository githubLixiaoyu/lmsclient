package com.zxtech.teacher.application.question.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.question.TKnowledgepointApplication;
import com.zxtech.manager.application.question.TQuestionCategoryApplication;
import com.zxtech.manager.application.question.TQuestionDifficultyApplication;
import com.zxtech.manager.application.question.TQuestionstypeApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.teacher.application.question.TQuestionsinfoApplication;
import com.zxtech.teacher.vo.question.QuestionInfoVo;
import com.zxtech.ui.dao.question.TQuestionKnowledgepointMapper;
import com.zxtech.ui.dao.question.TQuestionsOptionsMapper;
import com.zxtech.ui.dao.question.TQuestionsinfoMapper;
import com.zxtech.ui.vo.question.TKnowledgepoint;
import com.zxtech.ui.vo.question.TQuestionCategory;
import com.zxtech.ui.vo.question.TQuestionDifficulty;
import com.zxtech.ui.vo.question.TQuestionKnowledgepoint;
import com.zxtech.ui.vo.question.TQuestionsOptions;
import com.zxtech.ui.vo.question.TQuestionsinfo;
import com.zxtech.ui.vo.question.TQuestionsinfoWithBLOBs;
import com.zxtech.ui.vo.question.TQuestionstype;

@Named
public class TQuestionsinfoApplicationImpl implements TQuestionsinfoApplication{
	@Inject
	private TQuestionsinfoMapper tQuestionsinfoMapper;
	@Inject
	private TQuestionsOptionsMapper tQuestionsOptionsMapper;
	@Inject
	private TQuestionKnowledgepointMapper tQuestionKnowledgepointMapper;
	
	@Autowired
	private TQuestionstypeApplication tQuestionstypeApplication;
	@Autowired
	private TQuestionDifficultyApplication tQuestionDifficultyApplication;
	@Autowired
	private TQuestionCategoryApplication tQuestionCategoryApplication;
	@Autowired
	private TKnowledgepointApplication tKnowledgepointApplication;

	@Override
	public PageInfo<Map<String, Object>> selectQuestionsinfoList(TQuestionsinfo record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		List<Map<String, Object>> list = tQuestionsinfoMapper.selectQuestionsinfoList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveQuestionsinfo(QuestionInfoVo record) throws Exception{
		try{
			TQuestionsinfoWithBLOBs questioninfo = record.getQuestioninfo();
			boolean updateFlag = true;
			String questionId = questioninfo.getQuestionsid();
			if(questionId == null || "".equals(questionId)){
				questionId = UUID.randomUUID().toString();
				updateFlag = false;
			}
			questioninfo.setQuestionsid(questionId);
			questioninfo.setFlowstatus("0");
			questioninfo.setStatus("1");
			String answer = questioninfo.getAnswer();
			
			String[] answeArr = answer.split(",");
			String answerStr = "";
			
			if(questioninfo.getTypeid().equals("T00000000000002") || questioninfo.getTypeid().equals("T00000000000003")) {
				//保存选项
				List<TQuestionsOptions> questionOptionsList = record.getQuestionOptionsList();
				if(questionId != null && !"".equals(questionId)){
					tQuestionsOptionsMapper.deleteByQuestionsid(questionId);
				}
				for(int i=0;i<questionOptionsList.size();i++){
					TQuestionsOptions questionsOptions = questionOptionsList.get(i);
					String optionsid = UUID.randomUUID().toString();
					questionsOptions.setQuestionsid(questionId);
					questionsOptions.setOptionsid(optionsid);
					tQuestionsOptionsMapper.insertSelective(questionsOptions);
					for(int j=0;j<answeArr.length;j++){
						if(answeArr[j].equals(questionsOptions.getLevel()+"")){
							answerStr += optionsid+",";
						}
					}
				}
				if(answerStr.endsWith(",")){
					answerStr = answerStr.substring(0, answerStr.lastIndexOf(","));
				}
				questioninfo.setAnswer(answerStr);
				if(!"".equals(answerStr)) {
					questioninfo.setOptionstatus("1");
				}
			}
			
			if(questioninfo.getTypeid().equals("T00000000000006")) {
				questioninfo.setAttachstatus("1");
			}
			if(questioninfo.getKnowlegdePointsid() != null && !"".equals(questioninfo.getKnowlegdePointsid())) {
				String knowlegdePointsid[] = questioninfo.getKnowlegdePointsid().split(",");
				if(questionId != null && !"".equals(questionId)){
					tQuestionKnowledgepointMapper.deleteByQuestionsid(questionId);
				}
				for(int i = 0; i < knowlegdePointsid.length; i++) {
					TQuestionKnowledgepoint questionKnowledgePoint = new TQuestionKnowledgepoint();
					questionKnowledgePoint.setPointid(knowlegdePointsid[i]);
					questionKnowledgePoint.setQuestionid(questionId);
					questionKnowledgePoint.setStatus("1");
					tQuestionKnowledgepointMapper.insertSelective(questionKnowledgePoint);
				}
			}
			if(updateFlag){
				tQuestionsinfoMapper.updateByPrimaryKeySelective(questioninfo);
			}else{
				questioninfo.setCreatetime(new Date());
				questioninfo.setStaffid(AuthDetailUtil.getLoginUserId());
				tQuestionsinfoMapper.insertSelective(questioninfo);
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteQuestionsinfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			tQuestionsinfoMapper.deleteQuestionsinfo(ids);
			tQuestionsOptionsMapper.deleteQuestionsOptions(ids);
			tQuestionKnowledgepointMapper.deleteQuestionKnowledgepoint(ids);
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

	@Override
	public Map<String, Object> selectQuestionsinfoById(String questionsid) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		TQuestionstype record1 = new TQuestionstype();
		TQuestionDifficulty record2 = new TQuestionDifficulty();
		TQuestionCategory record3 = new TQuestionCategory();
		TKnowledgepoint record4 = new TKnowledgepoint();
		List<Map<String, Object>> questionstypeList = tQuestionstypeApplication.selectOptionQuestionstypeList(record1);
		List<Map<String, Object>> questionDifficultyList = tQuestionDifficultyApplication.selectOptionQuestionDifficultyList(record2);
		List<Map<String, Object>> questionCategoryList = tQuestionCategoryApplication.selectOptionQuestionCategoryList(record3);
		
		data.put("questionstypeList", questionstypeList);
		data.put("questionDifficultyList", questionDifficultyList);
		data.put("questionCategoryList", questionCategoryList);
		
		if(!"".equals(questionsid)){
			Map<String, Object> quesioninfo = tQuestionsinfoMapper.selectByPrimaryKey(questionsid);
			String pointsid = quesioninfo.get("pointsid") == null ? "" : quesioninfo.get("pointsid").toString();
			record4.setCategoryid(pointsid);
			List<Map<String, Object>> knowledgepointList = tKnowledgepointApplication.selectOptionKnowledgepointList(record4);
			data.put("knowledgepointList", knowledgepointList);
			String typeid = String.valueOf(quesioninfo.get("typeid"));
			List<Map<String, Object>> questionOptionsList = null;
			if("T00000000000002".equals(typeid) || "T00000000000003".equals(typeid)){
				TQuestionsOptions record = new TQuestionsOptions();
				record.setQuestionsid(questionsid);
				questionOptionsList = tQuestionsOptionsMapper.selectQuestionsOptionsList(record);
			}
			
			data.put("quesioninfo", quesioninfo);
			data.put("questionOptionsList", questionOptionsList);
		}
		
		return data;
	}

	@Override
	public int sendQuestionsinfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tQuestionsinfoMapper.sendQuestionsinfo(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"发布失败!");
		}
	}

	@Override
	public Map<String, Integer> selectQuestionNumByType(TQuestionsinfo record) {
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("difficulty1", 0);
		data.put("difficulty2", 0);
		data.put("difficulty3", 0);
		List<Map<String, Object>> list = tQuestionsinfoMapper.selectQuestionNumByType(record);
		int totalCount = 0;
		for(int i=0;i<list.size();i++){
			Map<String, Object> m = list.get(i);
			String difficultyId = m.get("difficulty").toString();
			String difficultyName = "";
			if(m.get("difficultyName") != null){
				difficultyName = m.get("difficultyName").toString();
			}
			int count = Integer.parseInt(m.get("count").toString());
			totalCount += count;
			if("1".equals(difficultyId) || "简单".equals(difficultyName)){
				data.put("difficulty1", data.get("difficulty1") + count);
			}else if("3".equals(difficultyId) || "中等".equals(difficultyName)){
				data.put("difficulty2", data.get("difficulty2") + count);
			}else if("5".equals(difficultyId) || "困难".equals(difficultyName)){
				data.put("difficulty3", data.get("difficulty3") + count);
			}
//			if("简单".equals(difficultyName)){
//				data.put("difficulty1", data.get("difficulty1") + count);
//			}else if("中等".equals(difficultyName)){
//				data.put("difficulty2", data.get("difficulty2") + count);
//			}else if("困难".equals(difficultyName)){
//				data.put("difficulty3", data.get("difficulty3") + count);
//			}
		}
		data.put("totalCount", totalCount);
		return data;
	}
		
}
