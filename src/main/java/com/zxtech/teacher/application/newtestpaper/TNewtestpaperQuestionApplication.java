package com.zxtech.teacher.application.newtestpaper;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperQuestion;

public interface TNewtestpaperQuestionApplication {
	//查询
	public PageInfo<Map<String, Object>> selectNewtestpaperQuestionList(TNewtestpaperQuestion record, int pageNum, int pageSize) throws Exception;
	//添加试题
	public int addNewtestpaperQuestion(String[] idList, String typeid, String paperid) throws Exception;
	//删除
	public int deleteNewtestpaperQuestion(TNewtestpaperQuestion record) throws Exception;
	public int deleteNewtestpaperQuestionById(String[] idList, String typeid, String paperid) throws Exception;
	//修改试题分数
	public int updateScore(String[] idList, String typeid, String paperid, String score) throws Exception;
	//更新总分
	public void updateNewtestpaperScore(String checkstatus, String typeid, String paperid) throws Exception;
}
