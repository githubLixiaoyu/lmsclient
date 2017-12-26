package com.zxtech.ui.application.course;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zxtech.ui.vo.app.TCourseInfo;
import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TUserLearningCourse;

public interface TCourseinfoApplication {
	
	Map<String, Object> selectByCourseType(HttpServletRequest request, TCourseInfo tCourseInfo) throws Exception;
	
	Map<String, Object> selectCourseDetailByCourseId(HttpServletRequest request, TCourseInfo tCourseInfo) throws Exception;
	
	Map<String, Object> selectCourseInfoByFillter(TCourseInfo tCourseInfo) throws Exception;
	
	int addPraise(TCoursePraise tCoursePraise) throws Exception;
	
	int removePraise(TCoursePraise tCoursePraise) throws Exception;
	
	int setUserLearnCourseScore(TUserLearningCourse tUserLearningCourse) throws Exception;
	
	int addUserLearnCourse(TUserLearningCourse tUserLearningCourse) throws Exception;
}
