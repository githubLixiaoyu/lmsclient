package com.zxtech.ui.dao.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperQuestion;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperQuestionKey;

public interface TNewtestpaperQuestionMapper {
    int deleteByPrimaryKey(TNewtestpaperQuestionKey key);

    int insert(TNewtestpaperQuestion record);

    int insertSelective(TNewtestpaperQuestion record);

    Map<String, Object> selectByPrimaryKey(TNewtestpaperQuestionKey key);

    int updateByPrimaryKeySelective(TNewtestpaperQuestion record);

    int updateByPrimaryKey(TNewtestpaperQuestion record);
    
    List<Map<String, Object>> selectNewtestpaperQuestionList(TNewtestpaperQuestion record);
    
    int deleteByTypeIdAndPaperId(TNewtestpaperQuestion record);
    
    List<Map<String, Object>> getPaperInfoById(TNewTestpaperInfo tNewTestpaperInfo);
    
    List<Map<String, Object>> selectPaperOptionsByFilter(TNewExamInfo tNewExamInfo);
    
    List<Map<String, Object>> selectPaperAnswersByFilter(TNewExamInfo tNewExamInfo);
    //插入随机试题
    int insertRandomQuestions(TNewtestpaperQuestion record);
    //删除随机试题
    int deleteRandomQuestions(TNewtestpaperQuestion record);
}