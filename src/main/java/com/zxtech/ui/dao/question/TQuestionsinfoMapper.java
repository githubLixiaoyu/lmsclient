package com.zxtech.ui.dao.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionsinfo;
import com.zxtech.ui.vo.question.TQuestionsinfoWithBLOBs;

public interface TQuestionsinfoMapper {
    int deleteByPrimaryKey(String questionsid);

    int insert(TQuestionsinfoWithBLOBs record);

    int insertSelective(TQuestionsinfoWithBLOBs record);

    Map<String, Object> selectByPrimaryKey(String questionsid);

    int updateByPrimaryKeySelective(TQuestionsinfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TQuestionsinfoWithBLOBs record);

    int updateByPrimaryKey(TQuestionsinfo record);
    
    List<Map<String, Object>> selectQuestionsinfoList(TQuestionsinfo record);
    
    int deleteQuestionsinfo(List<String> ids);
    
    int sendQuestionsinfo(List<String> ids);
    
    //查询试题各难度个数
    List<Map<String, Object>> selectQuestionNumByType(TQuestionsinfo record);
}