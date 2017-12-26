package com.zxtech.ui.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.manager.application.permissions.TPermissionsStudentinfoApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.support.i18n.I18NManager;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@RestController
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private TPermissionsStudentinfoApplication tPermissionsStudentinfoApplication;
	
	@RequestMapping("/loadMsg")
	public String loadMsg(String key, String[] args) {
		String message = I18NManager.getMessage(key, args);

		return message;
	}

	@RequestMapping("/loadAllMsg")
	public Map<String, Object> loadAllMsg(String locale) {
		return I18NManager.getAllMessage(locale);
	}
	
	@RequestMapping("/registerUser")
	public Result registerUser(TPermissionsStudentinfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.registerUser(record));
		return result;
	}
}
