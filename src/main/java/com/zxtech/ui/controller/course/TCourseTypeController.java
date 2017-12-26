package com.zxtech.ui.controller.course;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.course.TCourseTypeApplication;
import com.zxtech.ui.vo.app.TCourseType;

@RestController
@RequestMapping("/ui/tcoursetype")
public class TCourseTypeController {
	@Autowired
	private TCourseTypeApplication tCourseTypeApplication;

	/**
	 * 获取课程分类信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectAll")
	public Result selectAll(HttpServletRequest request, TCourseType tCourseType) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseTypeApplication.selectAll(request, tCourseType));
		return result;
	}

	/**
	 * 获取个人课程分类信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectAllByUserId")
	public Result selectAllByUserId(TCourseType tCourseType) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseTypeApplication.selectAllByUserId(tCourseType));
		return result;
	}
}
