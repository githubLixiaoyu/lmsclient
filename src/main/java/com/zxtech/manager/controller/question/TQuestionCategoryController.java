package com.zxtech.manager.controller.question;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.question.TQuestionCategoryApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.question.TQuestionCategory;


@RestController
@RequestMapping("/manager/tquestioncategory")
public class TQuestionCategoryController {
	@Autowired
	private TQuestionCategoryApplication tQuestionCategoryApplication;

	@RequestMapping("/selectQuestionCategoryList")
	public Result selectQuestionCategoryList(TQuestionCategory record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tQuestionCategoryApplication.selectQuestionCategoryList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveQuestionCategory")
	public Result saveQuestionCategory(TQuestionCategory record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tQuestionCategoryApplication.saveQuestionCategory(record));
		return result;
	}
	
	@RequestMapping("/deleteQuestionCategory")
	public Result deleteQuestionCategory(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tQuestionCategoryApplication.deleteQuestionCategory(idList));
		return result;
	}
	
	@RequestMapping("/selectOptionQuestionCategoryList")
	public Result selectOptionQuestionCategoryList(TQuestionCategory record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tQuestionCategoryApplication.selectOptionQuestionCategoryList(record);
		result.setData(list);
		return result;
	}
	
}
