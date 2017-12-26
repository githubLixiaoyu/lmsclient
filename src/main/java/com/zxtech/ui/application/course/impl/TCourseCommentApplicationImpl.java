package com.zxtech.ui.application.course.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.support.util.Util;
import com.zxtech.ui.application.course.TCourseCommentApplication;
import com.zxtech.ui.dao.app.TCourseCommentMapper;
import com.zxtech.ui.vo.app.TCourseComment;

@Named
public class TCourseCommentApplicationImpl implements TCourseCommentApplication{
	@Inject
	private TCourseCommentMapper tCourseCommentMapper;
	
	@Override
	public List<Map<String, Object>> getCourseCommentByCourseId(String courseId) throws Exception{
		List<Map<String, Object>> list = tCourseCommentMapper.getCourseCommentByCourseId(courseId);
		
		return list;
	}
	
	@Override
	public int addComment(TCourseComment tCourseComment) throws Exception{
		tCourseComment.setId(Util.getUUID());
		tCourseComment.setCreateDate(new Date());
		return tCourseCommentMapper.insertSelective(tCourseComment);
	}
}
