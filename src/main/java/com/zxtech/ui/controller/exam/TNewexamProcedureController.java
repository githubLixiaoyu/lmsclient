package com.zxtech.ui.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.exam.TNewexamProcedureApplication;
import com.zxtech.ui.vo.newexam.TNewexamProcedure;

@RestController
@RequestMapping("/ui/tnewexamprocedure")
public class TNewexamProcedureController {
	@Autowired
	private TNewexamProcedureApplication tNewexamProcedureApplication;

	/**
	 * 保存人员考试标记信息
	 * @param tNewexamProcedure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveExamProblemState")
	public Result saveExamProblemState(TNewexamProcedure tNewexamProcedure) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamProcedureApplication.saveExamProblemState(tNewexamProcedure));
		return result;
	}

	/**
	 * 保存人员考试分数信息
	 * @param tNewexamProcedure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveExamQuestionAnswer")
	public Result saveExamQuestionAnswer(TNewexamProcedure tNewexamProcedure) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamProcedureApplication.saveExamQuestionAnswer(tNewexamProcedure));
		return result;
	}
}
