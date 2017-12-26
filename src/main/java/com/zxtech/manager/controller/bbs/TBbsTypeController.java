package com.zxtech.manager.controller.bbs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.bbs.TBbsTypeApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.bbs.TBbsType;

@RestController
@RequestMapping("/manager/tbbstype")
public class TBbsTypeController {

	@Autowired
	private TBbsTypeApplication tBbsTypeApplication;

	@RequestMapping("/selectBbsTypePageList")
	public Result selectBbsTypePageList(TBbsType record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tBbsTypeApplication.selectBbsTypePageList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/selectBbsTypeList")
	public Result selectBbsTypeList(TBbsType record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tBbsTypeApplication.selectBbsTypeList(record));
		return result;
	}
}
