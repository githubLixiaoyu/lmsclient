package com.zxtech.manager.controller.bbs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.bbs.TBbsPostApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.bbs.TBbsPost;

@RestController
@RequestMapping("/manager/tbbspost")
public class TBbsPostController {

	@Autowired
	private TBbsPostApplication tBbsPostApplication;

	@RequestMapping("/selectBbsPostList")
	public Result selectBbsPostList(TBbsPost record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tBbsPostApplication.selectBbsPostList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/deleteBbsPost")
	public Result deleteBbsPost(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostApplication.deleteBbsPost(idList));
		return result;
	}
}
