package com.zxtech.manager.controller.newtestpaper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.newtestpaper.TNewtestpaperCategoryApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperCategory;


@RestController
@RequestMapping("/manager/tnewtestpapercategory")
public class TNewtestpaperCategoryController {
	@Autowired
	private TNewtestpaperCategoryApplication tNewtestpaperCategoryApplication;

	@RequestMapping("/selectNewtestpaperCategoryList")
	public Result selectNewtestpaperCategoryList(TNewtestpaperCategory record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tNewtestpaperCategoryApplication.selectNewtestpaperCategoryList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveNewtestpaperCategory")
	public Result saveNewtestpaperCategory(TNewtestpaperCategory record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewtestpaperCategoryApplication.saveNewtestpaperCategory(record));
		return result;
	}
	
	@RequestMapping("/deleteNewtestpaperCategory")
	public Result deleteNewtestpaperCategory(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewtestpaperCategoryApplication.deleteNewtestpaperCategory(idList));
		return result;
	}
	
}
