package com.zxtech.ui.dao.permissions;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;

public interface TPermissionsStudentDepartCourseMapper {
	List<Map<String,Object>> getCourseByDepartId(TPermissionsStudentDepart record);
}
