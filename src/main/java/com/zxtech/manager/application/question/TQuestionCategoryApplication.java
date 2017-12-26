package com.zxtech.manager.application.question;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.question.TQuestionCategory;

public interface TQuestionCategoryApplication {
	//查询
	public PageInfo<Map<String, Object>> selectQuestionCategoryList(TQuestionCategory record, int pageNum, int pageSize) throws Exception;
	//下拉框
	public List<Map<String, Object>> selectOptionQuestionCategoryList(TQuestionCategory record) throws Exception;
	//保存
	public int saveQuestionCategory(TQuestionCategory record) throws Exception;
	//删除
	public int deleteQuestionCategory(String[] idList) throws Exception;
}
