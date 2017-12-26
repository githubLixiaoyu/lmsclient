package com.zxtech.ui.application.course;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TCourseComment;

public interface TCourseCommentApplication {
	List<Map<String, Object>> getCourseCommentByCourseId(String courseId) throws Exception;
	
	int addComment(TCourseComment tCourseComment) throws Exception;
}
