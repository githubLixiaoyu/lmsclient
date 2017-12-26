package com.zxtech.teacher.controller.newtestpaper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.newtestpaper.TNewTestpaperInfoApplication;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;


@RestController
@RequestMapping("/teacher/tnewtestpaperinfo")
public class TNewTestpaperInfoController {
	@Autowired
	private TNewTestpaperInfoApplication tNewTestpaperInfoApplication;

	/**
	 * 添加试卷信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addNewtestpaperInfo")
	public Result addNewtestpaperInfo(TNewTestpaperInfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewTestpaperInfoApplication.addNewtestpaperInfo(record));
		return result;
	}

	/**
	 * 修改试卷信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateNewtestpaperInfo")
	public Result updateNewtestpaperInfo(TNewTestpaperInfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewTestpaperInfoApplication.updateNewtestpaperInfo(record));
		return result;
	}

	/**
	 * 获取试卷信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getNewtestpaperInfo")
	public Result getNewtestpaperInfo(TNewTestpaperInfo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewTestpaperInfoApplication.getNewtestpaperInfo(record));
		return result;
	}
	
	/**
	 * 获取试卷信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectNewTestpaperInfoList")
	public Result selectNewTestpaperInfoList(TNewTestpaperInfo record, int start, int length) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tNewTestpaperInfoApplication.selectNewTestpaperInfoList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	/**
	 * 发布
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendNewTestpaperInfo")
	public Result sendNewTestpaperInfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewTestpaperInfoApplication.sendNewTestpaperInfo(idList));
		return result;
	}
}
