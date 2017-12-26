package com.zxtech.teacher.application.vrnewexam;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.esp.vo.vrnewexam.TestInstanceRequestUsers;

public interface TestInstanceRequestUsersApplication {
	//查询
	public PageInfo<Map<String, Object>> selectTestInstanceRequestUsersList(TestInstanceRequestUsers record, int pageNum, int pageSize) throws Exception;
	public PageInfo<Map<String, Object>> selectCanSelectForTestInstanceRequestList(TestInstanceRequestUsers record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveTestInstanceRequestUsers(String[] idList, String testInstanceRequestId) throws Exception;
	//删除
	public int deleteTestInstanceRequestUsers(String[] idList, String testInstanceRequestId) throws Exception;
	//查询考试结果信息
	public PageInfo<Map<String, Object>> selectExamResultList(TestInstanceRequestUsers record, int pageNum, int pageSize) throws Exception;
}
