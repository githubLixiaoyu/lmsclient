package com.zxtech.ui.dao.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newtestpaper.TRandompaperQuestiontype;

public interface TRandompaperQuestiontypeMapper {
    int deleteByPrimaryKey(TRandompaperQuestiontype record);

    int insert(TRandompaperQuestiontype record);

    int insertSelective(TRandompaperQuestiontype record);

    Map<String, Object> selectByPrimaryKey(TRandompaperQuestiontype record);

    int updateByPrimaryKeySelective(TRandompaperQuestiontype record);

    int updateByPrimaryKey(TRandompaperQuestiontype record);

    List<Map<String, Object>> selectRandompaperQuestiontype(TRandompaperQuestiontype record);
    
    int updateByQuestion(TRandompaperQuestiontype record);
}