package com.zxtech.ui.controller.course;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.course.TCourseinfoApplication;
import com.zxtech.ui.vo.app.TCourseInfo;
import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TUserLearningCourse;

@RestController
@RequestMapping("/ui/tcourseinfo")
public class TCourseinfoController {
	@Autowired
	private TCourseinfoApplication tCourseinfoApplication;

	/**
	 * 获取该分类下的所有课程
	 * @param request
	 * @param tCourseInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectByCourseType")
	public Result selectByCourseType(HttpServletRequest request, TCourseInfo tCourseInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.selectByCourseType(request, tCourseInfo));
		return result;
	}

	/**
	 * 获取课程详细信息
	 * @param request
	 * @param tCourseInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectCourseDetailByCourseId")
	public Result selectCourseDetailByCourseId(HttpServletRequest request, TCourseInfo tCourseInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.selectCourseDetailByCourseId(request, tCourseInfo));
		return result;
	}

	/**
	 * 获取个人该分类下的所有课程
	 * @param tCourseInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectCourseInfoByFillter")
	public Result selectCourseInfoByFillter(TCourseInfo tCourseInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.selectCourseInfoByFillter(tCourseInfo));
		return result;
	}

	/**
	 * 点赞
	 * @param tCoursePraise
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addPraise")
	public Result addPraise(TCoursePraise tCoursePraise) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.addPraise(tCoursePraise));
		return result;
	}

	/**
	 * 取消点赞
	 * @param tCoursePraise
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/removePraise")
	public Result removePraise(TCoursePraise tCoursePraise) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.removePraise(tCoursePraise));
		return result;
	}

	/**
	 * 课程分数上传
	 * @param tUserLearningCourse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setUserLearnCourseScore")
	public Result setUserLearnCourseScore(TUserLearningCourse tUserLearningCourse) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.setUserLearnCourseScore(tUserLearningCourse));
		return result;
	}

	/**
	 * 添加课程学习记录
	 * @param tUserLearningCourse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUserLearnCourse")
	public Result addUserLearnCourse(TUserLearningCourse tUserLearningCourse) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseinfoApplication.addUserLearnCourse(tUserLearningCourse));
		return result;
	}
}
