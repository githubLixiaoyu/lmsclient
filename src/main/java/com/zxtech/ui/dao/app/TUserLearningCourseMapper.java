package com.zxtech.ui.dao.app;

import com.zxtech.ui.vo.app.TUserLearningCourse;
import com.zxtech.ui.vo.app.TUserLearningCourseKey;

public interface TUserLearningCourseMapper {
    int deleteByPrimaryKey(TUserLearningCourseKey key);

    int insert(TUserLearningCourse record);

    int insertSelective(TUserLearningCourse record);

    TUserLearningCourse selectByPrimaryKey(TUserLearningCourseKey key);

    int updateByPrimaryKeySelective(TUserLearningCourse record);

    int updateByPrimaryKey(TUserLearningCourse record);
    

}