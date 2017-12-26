package com.zxtech.ui.dao.permissions;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;

public interface TPermissionsStudentDepartMapper {
    int deleteByPrimaryKey(String departid);

    int insert(TPermissionsStudentDepart record);

    int insertSelective(TPermissionsStudentDepart record);

    TPermissionsStudentDepart selectByPrimaryKey(String departid);

    int updateByPrimaryKeySelective(TPermissionsStudentDepart record);

    int updateByPrimaryKey(TPermissionsStudentDepart record);
    
    List<Map<String, Object>> selectTPermissionsStudentDepartList(TPermissionsStudentDepart record);
    
    int inserTPermissionsStudentDepart(TPermissionsStudentDepart record);
    
    int updateTPermissionsStudentDepart(TPermissionsStudentDepart record);
    
    int deleteTPermissionsStudentDepart(List<String> ids);
}