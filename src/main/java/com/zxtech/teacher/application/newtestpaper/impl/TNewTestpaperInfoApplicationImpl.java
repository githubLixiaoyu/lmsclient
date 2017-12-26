package com.zxtech.teacher.application.newtestpaper.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.util.Util;
import com.zxtech.teacher.application.newtestpaper.TNewTestpaperInfoApplication;
import com.zxtech.ui.dao.newtestpaper.TNewTestpaperInfoMapper;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;

@Named
public class TNewTestpaperInfoApplicationImpl implements TNewTestpaperInfoApplication{
	@Inject
	private TNewTestpaperInfoMapper tNewTestpaperInfoMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> addNewtestpaperInfo(TNewTestpaperInfo record) throws Exception {
		record.setPaperid(Util.getUUID());
		record.setScore((short) 0);
		record.setPurpose("0");
		record.setCreater(AuthDetailUtil.getLoginUserId());
		record.setCreatetime(new Date());
		record.setStatus("0");
//		record.setPapertype("2");
		record.setPapermode("0");
		tNewTestpaperInfoMapper.insertSelective(record);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paperId", record.getPaperid());
		return map;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> updateNewtestpaperInfo(TNewTestpaperInfo record) throws Exception {
		record.setScore((short) 0);
		record.setPurpose("0");
		record.setCreater(AuthDetailUtil.getLoginUserId());
		record.setCreatetime(new Date());
		record.setStatus("0");
//		record.setPapertype("2");
		record.setPapermode("0");
		tNewTestpaperInfoMapper.updateByPrimaryKeySelective(record);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paperId", record.getPaperid());
		return map;
	}

	@Override
	public Map<String, Object> getNewtestpaperInfo(TNewTestpaperInfo record) throws Exception {
		return tNewTestpaperInfoMapper.getNewtestpaperInfo(record);
	}

	@Override
	public PageInfo<Map<String, Object>> selectNewTestpaperInfoList(TNewTestpaperInfo record, int pageNum, int pageSize)
			throws Exception {
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tNewTestpaperInfoMapper.selectNewTestpaperInfoList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int sendNewTestpaperInfo(String[] idList) throws Exception {
		try {
			List<String> ids = Arrays.asList(idList);
			return tNewTestpaperInfoMapper.sendNewTestpaperInfo(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"发布失败!");
		}
	}
}
