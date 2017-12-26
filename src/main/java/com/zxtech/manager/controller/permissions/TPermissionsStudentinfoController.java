package com.zxtech.manager.controller.permissions;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.permissions.TPermissionsStudentinfoApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;


@RestController
@RequestMapping("/manager/tpermissionsstudentinfo")
public class TPermissionsStudentinfoController {
	@Autowired
	private TPermissionsStudentinfoApplication tPermissionsStudentinfoApplication;

	@RequestMapping("/selectPermissionsStudentinfoList")
	public Result selectPermissionsStudentinfoList(TPermissionsStudentinfo record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tPermissionsStudentinfoApplication.selectPermissionsStudentinfoList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/savePermissionsStudentinfo")
	public Result savePermissionsStudentinfo(TPermissionsStudentinfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.savePermissionsStudentinfo(record));
		return result;
	}
	
	@RequestMapping("/deletePermissionsStudentinfo")
	public Result deletePermissionsStudentinfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.deletePermissionsStudentinfo(idList));
		return result;
	}
	
	@RequestMapping("/valideLoginCode")
	public Result valideLoginCode(TPermissionsStudentinfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.valideLoginCode(record));
		return result;
	}
	
}
