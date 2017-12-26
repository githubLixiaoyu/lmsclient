package com.zxtech.manager.controller.bbs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.bbs.TBbsPostReplyApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.bbs.TBbsPostReply;

@RestController
@RequestMapping("/manager/tbbspostreply")
public class TBbsPostReplyController {

	@Autowired
	private TBbsPostReplyApplication tBbsPostReplyApplication;

	@RequestMapping("/selectBbsPostReplyList")
	public Result selectBbsPostReplyList(TBbsPostReply record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tBbsPostReplyApplication.selectBbsPostReplyList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/deleteBbsPostReply")
	public Result deleteBbsPostReply(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsPostReplyApplication.deleteBbsPostReply(idList));
		return result;
	}
}
