package com.zxtech.manager.controller.question;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.question.TKnowledgepointApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.vo.question.TKnowledgepoint;


@RestController
@RequestMapping("/manager/tknowledgepoint")
public class TKnowledgepointController {
	@Autowired
	private TKnowledgepointApplication tKnowledgepointApplication;

	@RequestMapping("/selectKnowledgepointList")
	public Result selectKnowledgepointList(TKnowledgepoint record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tKnowledgepointApplication.selectKnowledgepointList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveKnowledgepoint")
	public Result saveKnowledgepoint(TKnowledgepoint record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tKnowledgepointApplication.saveKnowledgepoint(record));
		return result;
	}
	
	@RequestMapping("/deleteKnowledgepoint")
	public Result deleteKnowledgepoint(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tKnowledgepointApplication.deleteKnowledgepoint(idList));
		return result;
	}
	
}
