package com.zxtech.teacher.controller.newtestpaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.newtestpaper.TRandompaperQuestiontypeApplication;
import com.zxtech.ui.vo.newtestpaper.TRandompaperQuestiontype;


@RestController
@RequestMapping("/teacher/trandompaperquestiontype")
public class TRandompaperQuestiontypeController {
	@Autowired
	private TRandompaperQuestiontypeApplication tRandompaperQuestiontypeApplication;

	/**
	 * 获取试卷题型信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRandompaperQuestiontype")
	public Result selectRandompaperQuestiontype(TRandompaperQuestiontype record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tRandompaperQuestiontypeApplication.selectRandompaperQuestiontype(record));
		return result;
	}
}
