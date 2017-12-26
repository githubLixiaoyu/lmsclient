package com.zxtech.teacher.application.vrnewexam;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.esp.vo.vrnewexam.TestInstanceRequest;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

public interface TestInstanceRequestApplication {
	//查询
	public PageInfo<Map<String, Object>> selectTestInstanceRequestList(TestInstanceRequest record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveTestInstanceRequest(TestInstanceRequest record) throws Exception;
	//删除
	public int deleteTestInstanceRequest(String[] idList) throws Exception;
	//发布
	public int sendTestInstanceRequest(String[] idList) throws Exception;
	//查询需要编辑的信息
	public Map<String, Object> selectTestInstanceRequestById(String id) throws Exception;
	//查询全部人员信息
	public Map<String, Object> selectAllUserMap(TPermissionsStudentinfo record) throws Exception;
	//查询课程状态下拉框
	public List<Map<String, Object>> selectOptionStatusTypeList() throws Exception;
}
