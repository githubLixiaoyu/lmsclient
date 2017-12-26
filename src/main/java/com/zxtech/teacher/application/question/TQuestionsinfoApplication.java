package com.zxtech.teacher.application.question;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.teacher.vo.question.QuestionInfoVo;
import com.zxtech.ui.vo.question.TQuestionsinfo;

public interface TQuestionsinfoApplication {
	//查询
	public PageInfo<Map<String, Object>> selectQuestionsinfoList(TQuestionsinfo record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveQuestionsinfo(QuestionInfoVo record) throws Exception;
	//删除
	public int deleteQuestionsinfo(String[] idList) throws Exception;
	//发布
	public int sendQuestionsinfo(String[] idList) throws Exception;
	//查询需要编辑的信息
	public Map<String, Object> selectQuestionsinfoById(String questionsid) throws Exception;
	//查询试题各级难度总数
    Map<String, Integer> selectQuestionNumByType(TQuestionsinfo record);
}
