package com.zxtech.manager.controller.permissions;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.permissions.TPermissionsStudentDepartCourseApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;

@RestController
@RequestMapping("/manager/tpermissionsstudentdepartcourse")
public class TPermissionsStudentDepartCourseController {

	@Autowired
	private TPermissionsStudentDepartCourseApplication tPermissionsStudentDepartCourseApplication;
	@RequestMapping("/getCourseByDepartId")
	public Result getCourseByDepartId(TPermissionsStudentDepart record, int start, int length) throws Exception{		
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> list = tPermissionsStudentDepartCourseApplication.getCourseByDepartId(record, start / length + 1, length);
		result.setData(list);
		return result;
	}
}
