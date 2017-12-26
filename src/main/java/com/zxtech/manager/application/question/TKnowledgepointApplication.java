package com.zxtech.manager.application.question;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.question.TKnowledgepoint;

public interface TKnowledgepointApplication {
	//查询
	public PageInfo<Map<String, Object>> selectKnowledgepointList(TKnowledgepoint record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveKnowledgepoint(TKnowledgepoint record) throws Exception;
	//删除
	public int deleteKnowledgepoint(String[] idList) throws Exception;
	//下拉框
	public List<Map<String, Object>> selectOptionKnowledgepointList(TKnowledgepoint record) throws Exception;
}
