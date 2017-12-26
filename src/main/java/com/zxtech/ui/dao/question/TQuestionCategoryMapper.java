package com.zxtech.ui.dao.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionCategory;

public interface TQuestionCategoryMapper {
    int deleteByPrimaryKey(String categoryid);

    int insert(TQuestionCategory record);

    int insertSelective(TQuestionCategory record);

    Map<String, Object> selectByPrimaryKey(String categoryid);

    int updateByPrimaryKeySelective(TQuestionCategory record);

    int updateByPrimaryKeyWithBLOBs(TQuestionCategory record);

    int updateByPrimaryKey(TQuestionCategory record);
    
    List<Map<String, Object>> selectQuestionCategoryList(TQuestionCategory record);
    
    int deleteQuestionCategory(List<String> ids);
}