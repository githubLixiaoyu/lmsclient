package com.zxtech.ui.dao.app;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TCourseType;

public interface TCourseTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TCourseType record);

    int insertSelective(TCourseType record);

    Map<String,Object> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TCourseType record);

    int updateByPrimaryKey(TCourseType record);
    
    List<Map<String,Object>> selectCourseType(TCourseType record);
    
    List<Map<String,Object>> selectAll(TCourseType tCourseType);
    
    List<Map<String,Object>> selectAllByUserId(TCourseType tCourseType);
    
    List<Map<String,Object>> selectCourseTypeParent();
    
    List<Map<String,Object>> selectLearningCourseType(TCourseType record);
    
    List<Map<String, Object>> selectCourseTypeList(TCourseType record);
    
    int insertCourseType(TCourseType record);
    
    int updateCourseType(TCourseType record);
    
    Map<String, Object> selectTopSort(TCourseType record);
    
    int deleteCourseType(List<String> ids);
}