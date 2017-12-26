package com.zxtech.ui.dao.course;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.course.TCourseImage;

public interface TCourseImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TCourseImage record);

    int insertSelective(TCourseImage record);

    Map<String,Object> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TCourseImage record);

    int updateByPrimaryKey(TCourseImage record);
    
    List<Map<String,Object>> selectCourseImageList(TCourseImage record);
}