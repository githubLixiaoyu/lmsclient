package com.zxtech.teacher.controller.newtestpaper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.newtestpaper.TRandompaperDifficultySumApplication;
import com.zxtech.teacher.vo.newtestpaper.RandompaperDifficultySumVo;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySum;


@RestController
@RequestMapping("/teacher/trandompaperdifficultysum")
public class TRandompaperDifficultySumController {
	@Autowired
	private TRandompaperDifficultySumApplication tRandompaperDifficultySumApplication;

	@RequestMapping("/selectRandompaperDifficultySumList")
	public Result selectRandompaperDifficultySumList(TRandompaperDifficultySum record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tRandompaperDifficultySumApplication.selectRandompaperDifficultySumList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveRandompaperDifficultySum")
	public Result saveRandompaperDifficultySum(@RequestBody RandompaperDifficultySumVo record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tRandompaperDifficultySumApplication.saveRandompaperDifficultySum(record));
		return result;
	}
	
	@RequestMapping("/deleteRandompaperDifficultySum")
	public Result deleteRandompaperDifficultySum(@RequestParam(value = "idList[]") String[] idList, String typeid, String paperid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tRandompaperDifficultySumApplication.deleteRandompaperDifficultySum(idList, typeid, paperid));
		return result;
	}
	
	/**
	 * 删除试题
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteRandompaperQuestion")
	public Result deleteRandompaperQuestion(TRandompaperDifficultySum record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tRandompaperDifficultySumApplication.deleteRandompaperQuestion(record));
		return result;
	}
	
	@RequestMapping("/selectRandompaperDifficultySumById")
	public Result selectRandompaperDifficultySumById(TRandompaperDifficultySum record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tRandompaperDifficultySumApplication.selectRandompaperDifficultySumById(record));
		return result;
	}
}
