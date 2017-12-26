package com.zxtech.ui.application.course;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zxtech.ui.vo.app.TCourseType;

public interface TCourseTypeApplication {
	Map<String, Object> selectAll(HttpServletRequest request, TCourseType tCourseType) throws Exception;
	
	Map<String, Object> selectAllByUserId(TCourseType tCourseType) throws Exception;
}
