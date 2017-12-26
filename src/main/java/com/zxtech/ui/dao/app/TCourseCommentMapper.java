package com.zxtech.ui.dao.app;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TCourseComment;

public interface TCourseCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(TCourseComment record);

    int insertSelective(TCourseComment record);

    TCourseComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TCourseComment record);

    int updateByPrimaryKey(TCourseComment record);
    
    List<Map<String,Object>> getCourseCommentByCourseId(String courseId) ;
    
    List<Map<String,Object>> getCommentByCourseParent(String course_id) ;
    
    List<Map<String,Object>> getCommentByCourseChild(String course_id) ;
    
    Map<String,Object> getCommentNumByCourse(String course_id);
    
    List<Map<String,Object>> selectCourseCommentList(TCourseComment record);
    
    //删除
  	public int deleteCourseComment(List<String> ids);
}