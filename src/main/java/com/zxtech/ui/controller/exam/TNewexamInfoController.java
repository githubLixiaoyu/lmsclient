package com.zxtech.ui.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.exam.TNewexamInfoApplication;
import com.zxtech.ui.vo.newexam.TNewExamInfo;

@RestController
@RequestMapping("/ui/tnewexaminfo")
public class TNewexamInfoController {
	@Autowired
	private TNewexamInfoApplication tNewexamInfoApplication;

	/**
	 * 根据人员信息和考试分类检索考试信息
	 * @param tNewExamInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectExamInfoByFilter")
	public Result selectExamInfoByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamInfoApplication.selectPcExamInfoByFilter(tNewExamInfo));
		return result;
	}

	/**
	 * 根据人员ID和考试ID检索人员相关考试信息
	 * @param tNewExamInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserExamInfo")
	public Result getUserExamInfo(TNewExamInfo tNewExamInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamInfoApplication.getUserExamInfo(tNewExamInfo));
		return result;
	}
}
