package com.zxtech.teacher.controller.question;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.question.TQuestionsinfoApplication;
import com.zxtech.teacher.vo.question.QuestionInfoVo;
import com.zxtech.ui.vo.question.TQuestionsinfo;


@RestController
@RequestMapping("/teacher/tquestioninfo")
public class TQuestionsinfoController {
	@Autowired
	private TQuestionsinfoApplication tQuestionsinfoApplication;

	@RequestMapping("/selectQuestionsinfoList")
	public Result selectQuestionsinfoList(TQuestionsinfo record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tQuestionsinfoApplication.selectQuestionsinfoList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveQuestionsinfo")
	public Result saveQuestionsinfo(@RequestBody QuestionInfoVo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tQuestionsinfoApplication.saveQuestionsinfo(record));
		return result;
	}
	
	@RequestMapping("/deleteQuestionsinfo")
	public Result deleteQuestionsinfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tQuestionsinfoApplication.deleteQuestionsinfo(idList));
		return result;
	}
	
	@RequestMapping("/sendQuestionsinfo")
	public Result sendQuestionsinfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tQuestionsinfoApplication.sendQuestionsinfo(idList));
		return result;
	}
	
	@RequestMapping("/selectQuestionsinfoById")
	public Result selectQuestionsinfoById(String questionsid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tQuestionsinfoApplication.selectQuestionsinfoById(questionsid));
		return result;
	}
	
}
