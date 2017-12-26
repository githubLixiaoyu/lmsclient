package com.zxtech.teacher.application.newtestpaper.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.teacher.application.newtestpaper.TNewtestpaperQuestionApplication;
import com.zxtech.ui.dao.newtestpaper.TNewTestpaperInfoMapper;
import com.zxtech.ui.dao.newtestpaper.TNewtestpaperQuestionMapper;
import com.zxtech.ui.dao.newtestpaper.TRandompaperQuestiontypeMapper;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperQuestion;
import com.zxtech.ui.vo.newtestpaper.TRandompaperQuestiontype;

@Named
public class TNewtestpaperQuestionApplicationImpl implements TNewtestpaperQuestionApplication{
	@Inject
	private TNewtestpaperQuestionMapper tNewtestpaperQuestionMapper;
	@Inject
	private TRandompaperQuestiontypeMapper tRandompaperQuestiontypeMapper;
	@Inject
	private TNewTestpaperInfoMapper tNewTestpaperInfoMapper;
	
	@Override
	public PageInfo<Map<String, Object>> selectNewtestpaperQuestionList(TNewtestpaperQuestion record, int pageNum,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = tNewtestpaperQuestionMapper.selectNewtestpaperQuestionList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int addNewtestpaperQuestion(String[] idList, String typeid, String paperid) throws Exception {
		try{
			TNewtestpaperQuestion record = null;
			for(int i=0;i<idList.length;i++){
				record = new TNewtestpaperQuestion();
				record.setPaperid(paperid);
				record.setTypeid(typeid);
				record.setQuestionid(idList[i]);
				record.setDispaly("0");
				record.setScore(2.0);
				tNewtestpaperQuestionMapper.insertSelective(record);
			}
			if(idList.length > 0){
				updateNewtestpaperScore("1", record.getTypeid(), record.getPaperid());
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteNewtestpaperQuestion(TNewtestpaperQuestion record) throws Exception {
		try{
			tNewtestpaperQuestionMapper.deleteByTypeIdAndPaperId(record);
			updateNewtestpaperScore("0", record.getTypeid(), record.getPaperid());
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateScore(String[] idList, String typeid, String paperid, String score) throws Exception {
		try{
			TNewtestpaperQuestion record = null;
			for(int i=0;i<idList.length;i++){
				record = new TNewtestpaperQuestion();
				record.setPaperid(paperid);
				record.setTypeid(typeid);
				record.setQuestionid(idList[i]);
				record.setScore(Double.parseDouble(score));
				tNewtestpaperQuestionMapper.updateByPrimaryKeySelective(record);
			}
			if(idList.length > 0){
				updateNewtestpaperScore("", typeid, paperid);
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteNewtestpaperQuestionById(String[] idList, String typeid, String paperid) throws Exception {
		try {
			TNewtestpaperQuestion record = null;
			for(int i=0;i<idList.length;i++){
				record = new TNewtestpaperQuestion();
				record.setPaperid(paperid);
				record.setTypeid(typeid);
				record.setQuestionid(idList[i]);
				tNewtestpaperQuestionMapper.deleteByPrimaryKey(record);
			}
			if(idList.length > 0){
				updateNewtestpaperScore("", typeid, paperid);
			}
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateNewtestpaperScore(String checkstatus, String typeid, String paperid) throws Exception{
		try {
			TRandompaperQuestiontype tRandompaperQuestiontype = new TRandompaperQuestiontype();
			tRandompaperQuestiontype.setCheckstatus(checkstatus);
			tRandompaperQuestiontype.setPaperid(paperid);
			tRandompaperQuestiontype.setTypeid(typeid);
			tRandompaperQuestiontypeMapper.updateByQuestion(tRandompaperQuestiontype);
			TNewTestpaperInfo tNewTestpaperInfo = new TNewTestpaperInfo();
			tNewTestpaperInfo.setPaperid(paperid);
			tNewTestpaperInfoMapper.updateByPrimaryKeySelective(tNewTestpaperInfo);
		} catch (Exception e) {
			throw new BusinessException(e,"更新失败!");
		}
	}

}
