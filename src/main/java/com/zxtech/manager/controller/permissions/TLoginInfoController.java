package com.zxtech.manager.controller.permissions;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.permissions.TLoginInfoApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.permissions.TLoginInfo;


@RestController
@RequestMapping("/manager/tlogininfo")
public class TLoginInfoController {
	@Autowired
	private TLoginInfoApplication tLoginInfoApplication;

	@RequestMapping("/selectLoginInfoList")
	public Result selectLoginInfoList(TLoginInfo record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tLoginInfoApplication.selectLoginInfoList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
}
