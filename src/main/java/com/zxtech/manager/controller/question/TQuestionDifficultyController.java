package com.zxtech.manager.controller.question;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.manager.application.question.TQuestionDifficultyApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.question.TQuestionDifficulty;


@RestController
@RequestMapping("/manager/tquestiondifficulty")
public class TQuestionDifficultyController {
	@Autowired
	private TQuestionDifficultyApplication tQuestionDifficultyApplication;

	@RequestMapping("/selectOptionQuestionDifficultyList")
	public Result selectOptionQuestionDifficultyList(TQuestionDifficulty record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tQuestionDifficultyApplication.selectOptionQuestionDifficultyList(record);
		result.setData(list);
		return result;
	}
}
