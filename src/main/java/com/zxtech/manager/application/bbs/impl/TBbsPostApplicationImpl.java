package com.zxtech.manager.application.bbs.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.bbs.TBbsPostApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.ui.dao.bbs.TBbsPostMapper;
import com.zxtech.ui.vo.bbs.TBbsPost;

@Named
public class TBbsPostApplicationImpl implements TBbsPostApplication {
	
	@Inject
	private TBbsPostMapper tBbsPostMapper;

	@Override
	public PageInfo<Map<String, Object>> selectBbsPostList(TBbsPost record, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tBbsPostMapper.selectBbsPostList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteBbsPost(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tBbsPostMapper.deleteBbsPost(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

}
