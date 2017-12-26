package com.zxtech.ui.dao.permissions;

import com.zxtech.ui.vo.permissions.TPermissionsStudentRole;

public interface TPermissionsStudentRoleMapper {
    int deleteByPrimaryKey(TPermissionsStudentRole record);

    int insert(TPermissionsStudentRole record);

    int insertSelective(TPermissionsStudentRole record);
}