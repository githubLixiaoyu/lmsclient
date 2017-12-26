package com.zxtech.manager.application.course;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.app.TCourseComment;

public interface TCourseCommentApplication {
	//查询
	public PageInfo<Map<String, Object>> selectCourseCommentList(TCourseComment record, int pageNum, int pageSize) throws Exception;
	//删除
	public int deleteCourseComment(String[] idList) throws Exception;
}
