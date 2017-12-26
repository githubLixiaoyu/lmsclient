package com.zxtech.teacher.controller.newtestpaper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.newtestpaper.TNewtestpaperQuestionApplication;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperQuestion;


@RestController
@RequestMapping("/teacher/tnewtestpaperquestion")
public class TNewtestpaperQuestionController {
	@Autowired
	private TNewtestpaperQuestionApplication tNewtestpaperQuestionApplication;
	
	@RequestMapping("/selectNewtestpaperQuestionList")
	public Result selectNewtestpaperQuestionList(TNewtestpaperQuestion record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tNewtestpaperQuestionApplication.selectNewtestpaperQuestionList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}

	/**
	 * 添加试题
	 * @param idList
	 * @param typeid
	 * @param paperid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addNewtestpaperQuestion")
	public Result addNewtestpaperQuestion(@RequestParam(value = "idList[]") String[] idList, String typeid, String paperid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewtestpaperQuestionApplication.addNewtestpaperQuestion(idList, typeid, paperid));
		return result;
	}
	
	/**
	 * 删除试题
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteNewtestpaperQuestion")
	public Result deleteNewtestpaperQuestion(TNewtestpaperQuestion record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewtestpaperQuestionApplication.deleteNewtestpaperQuestion(record));
		return result;
	}
	@RequestMapping("/deleteNewtestpaperQuestionById")
	public Result deleteNewtestpaperQuestionById(String[] idList, String typeid, String paperid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewtestpaperQuestionApplication.deleteNewtestpaperQuestionById(idList, typeid, paperid));
		return result;
	}
	
	/**
	 * 修改试题分数
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateScore")
	public Result updateScore(@RequestParam(value = "idList[]") String[] idList, String typeid, String paperid, String score) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewtestpaperQuestionApplication.updateScore(idList, typeid, paperid, score));
		return result;
	}
}
