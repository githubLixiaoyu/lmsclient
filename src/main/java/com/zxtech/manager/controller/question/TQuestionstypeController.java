package com.zxtech.manager.controller.question;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.manager.application.question.TQuestionstypeApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.question.TQuestionstype;


@RestController
@RequestMapping("/manager/tquestionstype")
public class TQuestionstypeController {
	@Autowired
	private TQuestionstypeApplication tQuestionstypeApplication;

	@RequestMapping("/selectOptionQuestionstypeList")
	public Result selectOptionQuestionstypeList(TQuestionstype record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tQuestionstypeApplication.selectOptionQuestionstypeList(record);
		result.setData(list);
		return result;
	}
}
