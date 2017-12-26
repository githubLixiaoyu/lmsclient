package com.zxtech.teacher.application.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TQuestionsOptions;

public interface TQuestionsOptionsApplication {
	//查询
//	public PageInfo<Map<String, Object>> selectQuestionsOptionsPageList(TQuestionsOptions record, int pageNum, int pageSize) throws Exception;
	
	public List<Map<String, Object>> selectQuestionsOptionsList(TQuestionsOptions record) throws Exception;
	//保存
//	public int saveQuestionsOptions(QuestionInfoVo record) throws Exception;
//	//删除
//	public int deleteQuestionsOptions(String[] idList) throws Exception;
//	//查询需要编辑的信息
//	public Map<String, Object> selectQuestionsOptionsById(String questionsid) throws Exception;
}
