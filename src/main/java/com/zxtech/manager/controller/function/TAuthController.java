package com.zxtech.manager.controller.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.manager.application.function.TAuthApplication;
import com.zxtech.manager.vo.function.TAuth;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;


@RestController
@RequestMapping("/manager/tauth")
public class TAuthController {
	@Autowired
	private TAuthApplication tAuthApplication;

	@RequestMapping("/selectOptionAuthList")
	public Result selectOptionAuthList(TAuth record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tAuthApplication.selectOptionAuthList(record));
		return result;
	}
	
}
