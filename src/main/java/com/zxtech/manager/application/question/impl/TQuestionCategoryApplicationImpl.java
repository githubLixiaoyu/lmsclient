package com.zxtech.manager.application.question.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.question.TQuestionCategoryApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.ui.dao.question.TQuestionCategoryMapper;
import com.zxtech.ui.vo.question.TQuestionCategory;

@Named
public class TQuestionCategoryApplicationImpl implements TQuestionCategoryApplication{
	@Inject
	private TQuestionCategoryMapper tQuestionCategoryMapper;

	@Override
	public PageInfo<Map<String, Object>> selectQuestionCategoryList(TQuestionCategory record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = tQuestionCategoryMapper.selectQuestionCategoryList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveQuestionCategory(TQuestionCategory record) throws Exception{
		try{
			if(record.getCategoryid() != null && !"".equals(record.getCategoryid())){
				return tQuestionCategoryMapper.updateByPrimaryKeySelective(record);
			}else{
				UUID uuid = UUID.randomUUID();
				record.setCategoryid(uuid.toString());
				record.setCreatetime(new Date());
				record.setStandby1("0");
				record.setUserid(AuthDetailUtil.getLoginUserId());
				tQuestionCategoryMapper.insertSelective(record);
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteQuestionCategory(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tQuestionCategoryMapper.deleteQuestionCategory(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除人员失败!");
		}
	}

	@Override
	public List<Map<String, Object>> selectOptionQuestionCategoryList(TQuestionCategory record) throws Exception{
		List<Map<String, Object>> list = tQuestionCategoryMapper.selectQuestionCategoryList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("categoryid"));
			String text = String.valueOf(map.get("categoryname"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", text);
			returnList.add(m);
		}
		return returnList;
	}
		
}
