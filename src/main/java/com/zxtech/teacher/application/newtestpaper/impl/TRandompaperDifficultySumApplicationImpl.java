package com.zxtech.teacher.application.newtestpaper.impl;

import java.util.Arrays;
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
import com.zxtech.support.exception.BusinessException;
import com.zxtech.teacher.application.newtestpaper.TRandompaperDifficultySumApplication;
import com.zxtech.teacher.vo.newtestpaper.RandompaperDifficultySumVo;
import com.zxtech.ui.dao.newtestpaper.TNewTestpaperInfoMapper;
import com.zxtech.ui.dao.newtestpaper.TNewtestpaperQuestionMapper;
import com.zxtech.ui.dao.newtestpaper.TRandompaperDifficultyMapper;
import com.zxtech.ui.dao.newtestpaper.TRandompaperDifficultySumMapper;
import com.zxtech.ui.dao.newtestpaper.TRandompaperQuestiontypeMapper;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperQuestion;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficulty;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySum;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySumWithBLOBs;
import com.zxtech.ui.vo.newtestpaper.TRandompaperQuestiontype;
import com.zxtech.ui.vo.question.TKnowledgepoint;
import com.zxtech.ui.vo.question.TQuestionCategory;
import com.zxtech.ui.vo.question.TQuestionDifficulty;

@Named
public class TRandompaperDifficultySumApplicationImpl implements TRandompaperDifficultySumApplication{
	@Inject
	private TRandompaperDifficultySumMapper tRandompaperDifficultySumMapper;
	@Inject
	private TRandompaperDifficultyMapper tRandompaperDifficultyMapper;
	@Inject
	private TRandompaperQuestiontypeMapper tRandompaperQuestiontypeMapper;
	@Inject
	private TNewTestpaperInfoMapper tNewTestpaperInfoMapper;
	@Inject
	private TNewtestpaperQuestionMapper tNewtestpaperQuestionMapper;
	
	@Autowired
	private TQuestionCategoryApplication tQuestionCategoryApplication;
	@Autowired
	private TQuestionDifficultyApplication tQuestionDifficultyApplication;
	@Autowired
	private TKnowledgepointApplication tKnowledgepointApplication;

	@Override
	public PageInfo<Map<String, Object>> selectRandompaperDifficultySumList(TRandompaperDifficultySum record,
			int pageNum, int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = tRandompaperDifficultySumMapper.selectRandompaperDifficultySumList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	public Map<String, Object> selectRandompaperDifficultySumById(TRandompaperDifficultySum record) throws Exception {
		String id = record.getId();
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> difficultyMap = new HashMap<String, Object>();
		TQuestionDifficulty record2 = new TQuestionDifficulty();
		TQuestionCategory record3 = new TQuestionCategory();
		TKnowledgepoint record4 = new TKnowledgepoint();
		List<Map<String, Object>> questionDifficultyList = tQuestionDifficultyApplication.selectOptionQuestionDifficultyList(record2);
		List<Map<String, Object>> questionCategoryList = tQuestionCategoryApplication.selectOptionQuestionCategoryList(record3);
		data.put("questionCategoryList", questionCategoryList);
		
		for(int i=0;i<questionDifficultyList.size();i++){
			Map<String, Object> m = questionDifficultyList.get(i);
			difficultyMap.put(m.get("text").toString(), m.get("id"));
		}
		data.put("difficultyMap", difficultyMap);
		
		if(!"".equals(id)){
			Map<String, Object> randompaperDifficultySum = tRandompaperDifficultySumMapper.selectByPrimaryKey(id);
			String categoryid = randompaperDifficultySum.get("categoryid") == null ? "" : randompaperDifficultySum.get("categoryid").toString();
			record4.setCategoryid(categoryid);
			List<Map<String, Object>> knowledgepointList = tKnowledgepointApplication.selectOptionKnowledgepointList(record4);
			data.put("knowledgepointList", knowledgepointList);
			String difficultyid = String.valueOf(randompaperDifficultySum.get("id"));
			
			TRandompaperDifficulty randompaperDifficulty = new TRandompaperDifficulty();
			randompaperDifficulty.setDifficultyid(difficultyid);
			List<Map<String, Object>> randompaperDifficultyList = tRandompaperDifficultyMapper.selectRandompaperDifficultyList(randompaperDifficulty);
			Map<String, Object> numData = new HashMap<String, Object>();
			for(int i=0;i<randompaperDifficultyList.size();i++){
				Map<String, Object> m = randompaperDifficultyList.get(i);
				String difficultyId = m.get("difficulty").toString();
				String difficultyName = "";
				if(m.get("difficultyName") != null){
					difficultyName = m.get("difficultyName").toString();
				}
				Map<String, Object> randompaperDifficultyMap = new HashMap<String, Object>();
				randompaperDifficultyMap.put("totlequestions", m.get("totlequestions"));
				randompaperDifficultyMap.put("titlescore", m.get("titlescore"));
				if("0".equals(difficultyId) || "9".equals(difficultyId)){
					numData.put("summaryNum", randompaperDifficultyMap);
				}else if("1".equals(difficultyId) || "简单".equals(difficultyName)){
					numData.put("difficulty1", randompaperDifficultyMap);
				}else if("3".equals(difficultyId) || "中等".equals(difficultyName)){
					numData.put("difficulty2", randompaperDifficultyMap);
				}else if("5".equals(difficultyId) || "困难".equals(difficultyName)){
					numData.put("difficulty3", randompaperDifficultyMap);
				}
			}
			data.put("randompaperDifficultySum", randompaperDifficultySum);
			data.put("numData", numData);
		}
		
		return data;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveRandompaperDifficultySum(RandompaperDifficultySumVo record) throws Exception {
		try{
			TRandompaperDifficultySumWithBLOBs randompaperDifficultySum = record.getRandompaperDifficultySum();
			String typeid = randompaperDifficultySum.getTypeid();
			String paperid = randompaperDifficultySum.getPaperid();
			String id = randompaperDifficultySum.getId();
			if(id != null && !"".equals(id)){
				tRandompaperDifficultySumMapper.updateByPrimaryKeySelective(randompaperDifficultySum);
			}else{
				id = UUID.randomUUID().toString();
				randompaperDifficultySum.setId(id);
				tRandompaperDifficultySumMapper.insertSelective(randompaperDifficultySum);
			}
			//删除试题
			TNewtestpaperQuestion deleteQuestionParam = new TNewtestpaperQuestion();
			deleteQuestionParam.setDifficultydetailid(id);
			tNewtestpaperQuestionMapper.deleteRandomQuestions(deleteQuestionParam);
			//先删除
			TRandompaperDifficulty deleteParam = new TRandompaperDifficulty();
			deleteParam.setDifficultyid(id);
			tRandompaperDifficultyMapper.deleteRandompaperDifficultyById(deleteParam);
			//再添加
			List<TRandompaperDifficulty> randompaperDifficultyList = record.getRandompaperDifficultyList();
			for(int i=0;i<randompaperDifficultyList.size();i++){
				TRandompaperDifficulty tRandompaperDifficulty = randompaperDifficultyList.get(i);
				tRandompaperDifficulty.setId(UUID.randomUUID().toString());
				tRandompaperDifficulty.setDifficultyid(id);
				tRandompaperDifficultyMapper.insertSelective(tRandompaperDifficulty);
				//插入随机试题
				TNewtestpaperQuestion newtestpaperQuestion = new TNewtestpaperQuestion();
				newtestpaperQuestion.setPaperid(paperid);
				newtestpaperQuestion.setTypeid(typeid);
				newtestpaperQuestion.setScore(tRandompaperDifficulty.getTitlescore());
				newtestpaperQuestion.setDispaly("0");
				newtestpaperQuestion.setDifficultydetailid(tRandompaperDifficulty.getId());;
				newtestpaperQuestion.setDifficulty(tRandompaperDifficulty.getDifficulty());
				newtestpaperQuestion.setPointsid(randompaperDifficultySum.getCategoryid());
				double totlequestions = tRandompaperDifficulty.getTotlequestions();
				newtestpaperQuestion.setRandomNum((int)totlequestions);
				newtestpaperQuestion.setLoginUserId(AuthDetailUtil.getLoginUserId());
				tNewtestpaperQuestionMapper.insertRandomQuestions(newtestpaperQuestion);
			}
			//更新总分
			updateNewtestpaperScore("1", paperid, typeid);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateNewtestpaperScore(String checkstatus, String paperid, String typeid) throws Exception{
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("paperid", paperid);
			params.put("typeid", typeid);
			List<Map<String, Object>> list = tRandompaperDifficultyMapper.selectRandompaperDifficultyByPaperidList(params);
			double totalNum = 0d;
			double totalScore = 0d;
			double totlequestions = 0d;
			double titlescore = 0d;
			for(int i=0;i<list.size();i++){
				Map<String, Object> m = list.get(i);
				totlequestions = Double.parseDouble(m.get("totlequestions").toString());
				titlescore = Double.parseDouble(m.get("titlescore").toString());
				totalNum += totlequestions;
				totalScore += titlescore*totlequestions;
			}
			TRandompaperQuestiontype tRandompaperQuestiontype = new TRandompaperQuestiontype();
			tRandompaperQuestiontype.setCheckstatus(checkstatus);
			tRandompaperQuestiontype.setPaperid(paperid);
			tRandompaperQuestiontype.setTypeid(typeid);
			tRandompaperQuestiontype.setTitlescore(totalScore);
			tRandompaperQuestiontype.setTotlequestions(totalNum);
			//跟新各题型信息
			tRandompaperQuestiontypeMapper.updateByPrimaryKeySelective(tRandompaperQuestiontype);
			TNewTestpaperInfo tNewTestpaperInfo = new TNewTestpaperInfo();
			tNewTestpaperInfo.setPaperid(paperid);
			tNewTestpaperInfoMapper.updateByPrimaryKeySelective(tNewTestpaperInfo);
		} catch (Exception e) {
			throw new BusinessException(e,"更新失败!");
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteRandompaperDifficultySum(String[] idList, String paperid, String typeid) throws Exception {
		try {
			List<String> ids = Arrays.asList(idList);
			tRandompaperDifficultySumMapper.deleteRandompaperDifficultySum(ids);
			TRandompaperDifficulty randompaperDifficulty = new TRandompaperDifficulty();
			for(int i=0;i<idList.length;i++){
				randompaperDifficulty.setDifficultyid(idList[i]);
				//删除试题
				TNewtestpaperQuestion deleteQuestionParam = new TNewtestpaperQuestion();
				deleteQuestionParam.setDifficultydetailid(idList[i]);
				tNewtestpaperQuestionMapper.deleteRandomQuestions(deleteQuestionParam);
				tRandompaperDifficultyMapper.deleteRandompaperDifficultyById(randompaperDifficulty);
			}
			updateNewtestpaperScore("", typeid, paperid);
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteRandompaperQuestion(TRandompaperDifficultySum record) throws Exception {
		try{
			List<Map<String, Object>> list = tRandompaperDifficultySumMapper.selectRandompaperDifficultySumList(record);
			for(int i=0;i<list.size();i++){
				String id = list.get(i).get("id").toString();
				tRandompaperDifficultySumMapper.deleteByPrimaryKey(id);
				//删除试题
				TNewtestpaperQuestion deleteQuestionParam = new TNewtestpaperQuestion();
				deleteQuestionParam.setDifficultydetailid(id);
				tNewtestpaperQuestionMapper.deleteRandomQuestions(deleteQuestionParam);
				TRandompaperDifficulty tRandompaperDifficulty = new TRandompaperDifficulty();
				tRandompaperDifficulty.setDifficultyid(id);
				tRandompaperDifficultyMapper.deleteRandompaperDifficultyById(tRandompaperDifficulty);
			}
			String paperid = record.getPaperid();
			String typeid = record.getTypeid();
			updateNewtestpaperScore("0", paperid, typeid);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
