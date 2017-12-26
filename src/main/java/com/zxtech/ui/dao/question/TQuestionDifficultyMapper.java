package com.zxtech.ui.dao.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionDifficulty;

public interface TQuestionDifficultyMapper {
    int deleteByPrimaryKey(String difficultyId);

    int insert(TQuestionDifficulty record);

    int insertSelective(TQuestionDifficulty record);

    TQuestionDifficulty selectByPrimaryKey(String difficultyId);

    int updateByPrimaryKeySelective(TQuestionDifficulty record);

    int updateByPrimaryKey(TQuestionDifficulty record);
    
    List<Map<String, Object>> selectQuestionDifficultyList(TQuestionDifficulty record);
}