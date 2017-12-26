package com.zxtech.ui.dao.question;

import java.util.List;

import com.zxtech.ui.vo.question.TQuestionHistory;

public interface TQuestionHistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(TQuestionHistory record);

    int insertSelective(TQuestionHistory record);

    TQuestionHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TQuestionHistory record);

    int updateByPrimaryKey(TQuestionHistory record);
    
    int deleteQuestionHistory(List<String> ids);
}