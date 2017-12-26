package com.zxtech.manager.controller.course;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.course.TCourseCommentApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.app.TCourseComment;

@RestController
@RequestMapping("/manager/tcoursecomment")
public class TCourseCommentController {
	
	@Autowired
	private TCourseCommentApplication tCourseCommentApplication;

	@RequestMapping("/selectCourseCommentList")
	public Result selectCourseCommentList(TCourseComment record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tCourseCommentApplication.selectCourseCommentList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/deleteCourseComment")
	public Result deleteCourseComment(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseCommentApplication.deleteCourseComment(idList));
		return result;
	}
	
}
