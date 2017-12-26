package com.zxtech.manager.application.permissions;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

public interface TPermissionsStudentinfoApplication {
	//查询
	public PageInfo<Map<String, Object>> selectPermissionsStudentinfoList(TPermissionsStudentinfo record, int pageNum, int pageSize) throws Exception;
	//保存
	public int savePermissionsStudentinfo(TPermissionsStudentinfo record) throws Exception;
	//删除
	public int deletePermissionsStudentinfo(String[] idList) throws Exception;
	//根据登录名查询用户
	public Map<String, Object> findUserByLoginCode(String loginCode) throws Exception;
	//校验登录名
	public boolean valideLoginCode(TPermissionsStudentinfo record) throws Exception;
	//注册
	public Map<String, Object> registerUser(TPermissionsStudentinfo record) throws Exception;
}
