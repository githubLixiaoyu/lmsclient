package com.zxtech.teacher.controller.newexam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.newexam.TNewExamInfoApplication;
import com.zxtech.ui.vo.newexam.TNewExamInfo;


@RestController
@RequestMapping("/teacher/tnewexaminfo")
public class TNewExamInfoController {
	@Autowired
	private TNewExamInfoApplication tNewExamInfoApplication;

	@RequestMapping("/selectNewExamInfoList")
	public Result selectNewExamInfoList(TNewExamInfo record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tNewExamInfoApplication.selectNewExamInfoList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveNewExamInfo")
	public Result saveNewExamInfo(TNewExamInfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewExamInfoApplication.saveNewExamInfo(record));
		return result;
	}
	
	@RequestMapping("/deleteNewExamInfo")
	public Result deleteNewExamInfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewExamInfoApplication.deleteNewExamInfo(idList));
		return result;
	}
	
	@RequestMapping("/sendNewExamInfo")
	public Result sendNewExamInfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewExamInfoApplication.sendNewExamInfo(idList));
		return result;
	}
	
	@RequestMapping("/selectNewExamInfoById")
	public Result selectNewExamInfoById(String examid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewExamInfoApplication.selectNewExamInfoById(examid));
		return result;
	}
	
}
