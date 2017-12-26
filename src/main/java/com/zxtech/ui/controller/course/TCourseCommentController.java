package com.zxtech.ui.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.course.TCourseCommentApplication;
import com.zxtech.ui.vo.app.TCourseComment;

@RestController
@RequestMapping("/ui/tcoursecomment")
public class TCourseCommentController {
	@Autowired
	private TCourseCommentApplication tCourseCommentApplication;

	/**
	 * 获取所属课程的评论
	 * @param request
	 * @param tCourseInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCourseCommentByCourseId")
	public Result getCourseCommentByCourseId(String courseId) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseCommentApplication.getCourseCommentByCourseId(courseId));
		return result;
	}

	/**
	 * 添加评论
	 * @param request
	 * @param tCourseInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addComment")
	public Result addComment(TCourseComment tCourseComment) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseCommentApplication.addComment(tCourseComment));
		return result;
	}

	/**
	 * 回复评论
	 * @param request
	 * @param tCourseInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/replyComment")
	public Result replyComment(TCourseComment tCourseComment) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseCommentApplication.addComment(tCourseComment));
		return result;
	}
	
	
}
