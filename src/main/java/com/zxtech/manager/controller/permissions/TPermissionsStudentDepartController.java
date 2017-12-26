package com.zxtech.manager.controller.permissions;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.permissions.TPermissionsStudentDepartApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;


@RestController
@RequestMapping("/manager/tpermissionsstudentdepart")
public class TPermissionsStudentDepartController {
	@Autowired
	private TPermissionsStudentDepartApplication tPermissionsStudentDepartApplication;

	@RequestMapping("/selectPermissionsStudentDepartList")
	public Result selectPermissionsStudentDepartList(TPermissionsStudentDepart record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tPermissionsStudentDepartApplication.selectPermissionsStudentDepartList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/selectAllPermissionsStudentDepartList")
	public Result selectAllPermissionsStudentDepartList(TPermissionsStudentDepart record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tPermissionsStudentDepartApplication.selectAllPermissionsStudentDepartList(record);
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/savePermissionsStudentDepart")
	public Result savePermissionsStudentDepart(TPermissionsStudentDepart record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentDepartApplication.savePermissionsStudentDepart(record));
		return result;
	}
	
	@RequestMapping("/deletePermissionsStudentDepart")
	public Result deletePermissionsStudentDepart(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentDepartApplication.deletePermissionsStudentDepart(idList));
		return result;
	}
	
	@RequestMapping("/valideDepartName")
	public Result valideDepartName(TPermissionsStudentDepart record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentDepartApplication.valideDepartName(record));
		return result;
	}	
}
