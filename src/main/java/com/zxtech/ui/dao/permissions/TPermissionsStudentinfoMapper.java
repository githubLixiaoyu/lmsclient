package com.zxtech.ui.dao.permissions;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TCourseComment;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

public interface TPermissionsStudentinfoMapper {
    int deleteByPrimaryKey(String studentid);

    int insert(TPermissionsStudentinfo record);

    int insertSelective(TPermissionsStudentinfo record);

    Map<String, Object> selectByPrimaryKey(String studentid);

    int updateByPrimaryKeySelective(TPermissionsStudentinfo record);

    int updateByPrimaryKeyWithBLOBs(TPermissionsStudentinfo record);

    int updateByPrimaryKey(TPermissionsStudentinfo record);

    List<Map<String, Object>> login(String logincode);
    
    List<Map<String, Object>> mlogin(String logincode);
    
    TCourseComment selectById(String id);
    
    List<Map<String, Object>> selectTPermissionsStudentinfoList(TPermissionsStudentinfo record);
    
    List<Map<String, Object>> selectTPermissionsStudentinfoAllList(TPermissionsStudentinfo record);
    
    int inserTPermissionsStudentinfo(TPermissionsStudentinfo record);
    
    int updateTPermissionsStudentinfo(TPermissionsStudentinfo record);
    
    int deleteTPermissionsStudentinfo(List<String> ids);
    
    List<Map<String, Object>> selectAllUserList();
}