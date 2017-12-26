package com.zxtech.ui.application.permissions;

import java.util.Map;

import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

public interface TPermissionsStudentinfoApplication {
	int deleteByPrimaryKey(String studentid) throws Exception;
	int insert(TPermissionsStudentinfo record) throws Exception;
	int insertSelective(TPermissionsStudentinfo record) throws Exception;
	Map<String, Object> selectByPrimaryKey(String studentid) throws Exception;
	int updateByPrimaryKeySelective(TPermissionsStudentinfo record) throws Exception;
	int updateByPrimaryKeyWithBLOBs(TPermissionsStudentinfo record) throws Exception;
	int updateByPrimaryKey(TPermissionsStudentinfo record) throws Exception;
	Map<String, Object> selectLoginUser() throws Exception;
	Map<String, Object> getUserInfo() throws Exception;
	int updateNickName(TPermissionsStudentinfo record) throws Exception;
	int updatePassword(TPermissionsStudentinfo record) throws Exception;
	int updatePhotoName(String photoName) throws Exception;
}
