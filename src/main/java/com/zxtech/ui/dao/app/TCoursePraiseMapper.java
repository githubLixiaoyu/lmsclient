package com.zxtech.ui.dao.app;

import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TCoursePraiseExample;
import java.util.List;
import java.util.Map;

public interface TCoursePraiseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TCoursePraise record);

    int insertSelective(TCoursePraise record);

    List<TCoursePraise> selectByExample(TCoursePraiseExample example);

    TCoursePraise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TCoursePraise record);

    int updateByPrimaryKey(TCoursePraise record);
    
    int deleteByCourseIdAndUserId(TCoursePraise record);
    
    Map<String,Object> getPraiseNumByCourse(String course_id);
}