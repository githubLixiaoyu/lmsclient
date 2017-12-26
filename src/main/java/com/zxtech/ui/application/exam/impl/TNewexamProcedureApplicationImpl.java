package com.zxtech.ui.application.exam.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zxtech.ui.application.exam.TNewexamProcedureApplication;
import com.zxtech.ui.dao.newexam.TNewexamDetailsMapper;
import com.zxtech.ui.dao.newexam.TNewexamProcedureMapper;
import com.zxtech.ui.vo.newexam.TNewexamDetails;
import com.zxtech.ui.vo.newexam.TNewexamProcedure;

@Named
public class TNewexamProcedureApplicationImpl implements TNewexamProcedureApplication{
	@Inject
	private TNewexamProcedureMapper tNewexamProcedureMapper;
	@Inject
	private TNewexamDetailsMapper tNewexamDetailsMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveExamProblemState(TNewexamProcedure tNewexamProcedure) throws Exception{
		Map<String, Object> map = tNewexamProcedureMapper.selectByPrimaryKey(tNewexamProcedure);
		
		int returnFlag = 0;
		if (map == null) {
			returnFlag = tNewexamProcedureMapper.insertSelective(tNewexamProcedure);
		} else {
			TNewexamProcedure tnp = new TNewexamProcedure();
			tnp.setExamid(tNewexamProcedure.getExamid());
			tnp.setPaperid(tNewexamProcedure.getPaperid());
			tnp.setUserid(tNewexamProcedure.getUserid());
			tnp.setQuestionid(tNewexamProcedure.getQuestionid());
			tnp.setProblemstate(tNewexamProcedure.getProblemstate());
			returnFlag = tNewexamProcedureMapper.updateByPrimaryKeySelective(tnp);
		}
		TNewexamDetails tNewexamDetails = new TNewexamDetails();
		tNewexamDetails.setExamid(tNewexamProcedure.getExamid());
		tNewexamDetails.setPaperid(tNewexamProcedure.getPaperid());
		tNewexamDetails.setUserid(tNewexamProcedure.getUserid());
		tNewexamDetails.setContinuetime(tNewexamProcedure.getContinuetime());
		tNewexamDetailsMapper.updateByPrimaryKeySelective(tNewexamDetails);
		return returnFlag;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveExamQuestionAnswer(TNewexamProcedure tNewexamProcedure) throws Exception{
		Map<String, Object> map = tNewexamProcedureMapper.selectByPrimaryKey(tNewexamProcedure);
		
		int returnFlag = 0;
		if (map == null) {
			returnFlag = tNewexamProcedureMapper.insertSelective(tNewexamProcedure);
		} else {
			TNewexamProcedure tnp = new TNewexamProcedure();
			tnp.setExamid(tNewexamProcedure.getExamid());
			tnp.setPaperid(tNewexamProcedure.getPaperid());
			tnp.setUserid(tNewexamProcedure.getUserid());
			tnp.setQuestionid(tNewexamProcedure.getQuestionid());
			tnp.setAnswer(tNewexamProcedure.getAnswer());
			returnFlag = tNewexamProcedureMapper.updateByPrimaryKeySelective(tnp);
		}
		TNewexamDetails tNewexamDetails = new TNewexamDetails();
		tNewexamDetails.setExamid(tNewexamProcedure.getExamid());
		tNewexamDetails.setPaperid(tNewexamProcedure.getPaperid());
		tNewexamDetails.setUserid(tNewexamProcedure.getUserid());
		tNewexamDetails.setContinuetime(tNewexamProcedure.getContinuetime());
		tNewexamDetailsMapper.updateByPrimaryKeySelective(tNewexamDetails);
		return returnFlag;
	}
}
