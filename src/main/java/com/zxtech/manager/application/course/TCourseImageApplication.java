package com.zxtech.manager.application.course;

import java.util.Map;

public interface TCourseImageApplication {
//	//查询
//	public PageInfo<Map<String, Object>> selectCourseImageList(TCourseImage record, int pageNum, int pageSize) throws Exception;
//	//保存
//	public int saveCourseImage(TCourseImage record) throws Exception;
//	//删除
//	public int deleteCourseImage(String[] idList) throws Exception;
	
	public Map<String, Object> selectTCourseImageByCourseid(String courseid) throws Exception;
}
