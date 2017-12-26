package com.zxtech.ui.dao.question;

import java.util.List;

import com.zxtech.ui.vo.question.TQuestionKnowledgepoint;

public interface TQuestionKnowledgepointMapper {
    int insert(TQuestionKnowledgepoint record);

    int insertSelective(TQuestionKnowledgepoint record);
    
    int deleteByQuestionsid(String questionsid);
    
    int deleteQuestionKnowledgepoint(List<String> ids);
}