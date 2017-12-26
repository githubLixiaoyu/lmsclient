package com.zxtech.ui.application.exam.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zxtech.ui.application.exam.TNewexamDetailsApplication;
import com.zxtech.ui.dao.newexam.TNewexamDetailsMapper;
import com.zxtech.ui.dao.newexam.TNewexamMarkMapper;
import com.zxtech.ui.vo.newexam.TNewexamDetails;
import com.zxtech.ui.vo.newexam.TNewexamMark;

@Named
public class TNewexamDetailsApplicationImpl implements TNewexamDetailsApplication{
	@Inject
	private TNewexamDetailsMapper tNewexamDetailsMapper;
	@Inject
	private TNewexamMarkMapper tNewexamMarkMapper;
	
	@Override
	public int saveExamDetails(TNewexamDetails tNewexamDetails) throws Exception{
		Map<String, Object> map = tNewexamDetailsMapper.selectByPrimaryKey(tNewexamDetails);
		int returnFlag = 0;
		if (map == null) {
			tNewexamDetails.setStarttime(new Date());
			tNewexamDetails.setReviewstatus("0");
			tNewexamDetails.setContinuetime(0d);
			tNewexamDetails.setIfapplay("0");
			tNewexamDetails.setIfappend("0");
			returnFlag = tNewexamDetailsMapper.insertSelective(tNewexamDetails);
		} else {
			returnFlag = 1;
		}
		return returnFlag;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> saveUserExam(TNewexamDetails tNewexamDetails) throws Exception{
		List<Map<String, Object>> scoreList = tNewexamDetailsMapper.selectExamScoreByFilter(tNewexamDetails);
		double d = 0;
		for (Map<String, Object> sm : scoreList) {
			String answer = String.valueOf(sm.get("answer"));
			String useranswer = String.valueOf(sm.get("useranswer"));
			if (answer.equals(useranswer)) {
				d += Double.valueOf(String.valueOf(sm.get("score")));
			}
		}
		BigDecimal bg = BigDecimal.valueOf(d);
		TNewexamMark tNewexamMark = new TNewexamMark();
		tNewexamMark.setExamid(tNewexamDetails.getExamid());
		tNewexamMark.setPaperid(tNewexamDetails.getPaperid());
		tNewexamMark.setUserid(tNewexamDetails.getUserid());
		tNewexamMark.setScore(bg);
		tNewexamMarkMapper.insertSelective(tNewexamMark);
		
		tNewexamDetails.setEndtime(new Date());
		tNewexamDetails.setReviewstatus("1");
		tNewexamDetailsMapper.updateByPrimaryKeySelective(tNewexamDetails);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("score", d);
		
		return map;
	}
}
