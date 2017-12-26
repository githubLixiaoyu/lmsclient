package com.zxtech.teacher.application.newexam;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.newexam.TNewExamInfo;

public interface TNewExamInfoApplication {
	//查询
	public PageInfo<Map<String, Object>> selectNewExamInfoList(TNewExamInfo record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveNewExamInfo(TNewExamInfo record) throws Exception;
	//删除
	public int deleteNewExamInfo(String[] idList) throws Exception;
	//发布
	public int sendNewExamInfo(String[] idList) throws Exception;
	//查询需要编辑的信息
	public Map<String, Object> selectNewExamInfoById(String examid) throws Exception;
}
