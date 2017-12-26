package com.zxtech.manager.application.course.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.course.TCourseCommentApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.ui.dao.app.TCourseCommentMapper;
import com.zxtech.ui.vo.app.TCourseComment;

@Named
public class TCourseCommentApplicationImpl implements TCourseCommentApplication {
	
	@Inject
	private TCourseCommentMapper tCourseCommentMapper;

	@Override
	public PageInfo<Map<String, Object>> selectCourseCommentList(TCourseComment record, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tCourseCommentMapper.selectCourseCommentList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	public int deleteCourseComment(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tCourseCommentMapper.deleteCourseComment(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

}
