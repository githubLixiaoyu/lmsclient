package com.zxtech.ui.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.app.TAppApplication;

@RestController
@RequestMapping("/ui/tapp")
public class TAppController {
	@Autowired
	private TAppApplication tAppApplication;

	/**
	 * 获取APP二维码图片
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getQrcode")
	public Result getQrcode(HttpServletRequest request) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tAppApplication.getQrcode(request));
		return result;
	}
}
