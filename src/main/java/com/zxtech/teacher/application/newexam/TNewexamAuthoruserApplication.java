package com.zxtech.teacher.application.newexam;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.newexam.TNewexamAuthoruser;

public interface TNewexamAuthoruserApplication {
	//查询
	public PageInfo<Map<String, Object>> selectNewexamAuthoruserList(TNewexamAuthoruser record, int pageNum, int pageSize) throws Exception;
	public PageInfo<Map<String, Object>> selectCanSelectAuthoruserList(TNewexamAuthoruser record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveNewexamAuthoruser(String[] idList, String examid) throws Exception;
	//删除
	public int deleteNewexamAuthoruser(String[] idList, String examid) throws Exception;
	//查询考试结果信息
	public PageInfo<Map<String, Object>> selectExamResultList(TNewexamAuthoruser record, int pageNum, int pageSize) throws Exception;
}
