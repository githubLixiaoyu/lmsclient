package com.zxtech.manager.application.permissions;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;

public interface TPermissionsStudentDepartApplication {
	//查询
	public PageInfo<Map<String, Object>> selectPermissionsStudentDepartList(TPermissionsStudentDepart record, int pageNum, int pageSize) throws Exception;
	//查询全部
	public List<Map<String, Object>> selectAllPermissionsStudentDepartList(TPermissionsStudentDepart record) throws Exception;
	//保存
	public int savePermissionsStudentDepart(TPermissionsStudentDepart record) throws Exception;
	//删除
	public int deletePermissionsStudentDepart(String[] idList) throws Exception;
	//根据登录名查询用户
	public Map<String, Object> findDepartByName(String departName) throws Exception;
	//校验公司名
	public boolean valideDepartName(TPermissionsStudentDepart record) throws Exception;
}
