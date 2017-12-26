package com.zxtech.ui.dao.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionsOptions;

public interface TQuestionsOptionsMapper {
    int deleteByPrimaryKey(String optionsid);

    int insert(TQuestionsOptions record);

    int insertSelective(TQuestionsOptions record);

    TQuestionsOptions selectByPrimaryKey(String optionsid);

    int updateByPrimaryKeySelective(TQuestionsOptions record);

    int updateByPrimaryKeyWithBLOBs(TQuestionsOptions record);

    int updateByPrimaryKey(TQuestionsOptions record);
    
    List<Map<String, Object>> selectQuestionsOptionsList(TQuestionsOptions record);
    
    int deleteByQuestionsid(String questionsid);
    
    int deleteQuestionsOptions(List<String> ids);
}