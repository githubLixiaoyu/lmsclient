package com.zxtech.ui.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.exam.TNewexamDetailsApplication;
import com.zxtech.ui.vo.newexam.TNewexamDetails;

@RestController
@RequestMapping("/ui/tnewexamdetails")
public class TNewexamDetailsController {
	@Autowired
	private TNewexamDetailsApplication tNewexamDetailsApplication;

	/**
	 * 保存人员考试基本信息
	 * @param tNewexamDetails
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveExamDetails")
	public Result saveExamDetails(TNewexamDetails tNewexamDetails) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamDetailsApplication.saveExamDetails(tNewexamDetails));
		return result;
	}

	/**
	 * 交卷
	 * @param tNewexamDetails
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUserExam")
	public Result saveUserExam(TNewexamDetails tNewexamDetails) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamDetailsApplication.saveUserExam(tNewexamDetails));
		return result;
	}
}
