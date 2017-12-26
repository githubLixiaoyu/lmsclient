package com.zxtech.ui.dao.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionstype;

public interface TQuestionstypeMapper {
    int deleteByPrimaryKey(String typeid);

    int insert(TQuestionstype record);

    int insertSelective(TQuestionstype record);

    TQuestionstype selectByPrimaryKey(String typeid);

    int updateByPrimaryKeySelective(TQuestionstype record);

    int updateByPrimaryKey(TQuestionstype record);
    
    List<Map<String, Object>> selectQuestionstypeList(TQuestionstype record);
}