package com.zxtech.teacher.controller.newexam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.newexam.TNewexamAuthoruserApplication;
import com.zxtech.ui.vo.newexam.TNewexamAuthoruser;


@RestController
@RequestMapping("/teacher/tnewexamauthoruser")
public class TNewexamAuthoruserController {
	@Autowired
	private TNewexamAuthoruserApplication tNewexamAuthoruserApplication;

	@RequestMapping("/selectNewexamAuthoruserList")
	public Result selectNewexamAuthoruserList(TNewexamAuthoruser record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tNewexamAuthoruserApplication.selectNewexamAuthoruserList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/selectCanSelectAuthoruserList")
	public Result selectCanSelectAuthoruserList(TNewexamAuthoruser record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tNewexamAuthoruserApplication.selectCanSelectAuthoruserList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/saveNewexamAuthoruser")
	public Result saveNewexamAuthoruser(@RequestParam(value = "idList[]") String[] idList, String examid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamAuthoruserApplication.saveNewexamAuthoruser(idList, examid));
		return result;
	}
	
	@RequestMapping("/deleteNewexamAuthoruser")
	public Result deleteNewexamAuthoruser(@RequestParam(value = "idList[]") String[] idList, String examid) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tNewexamAuthoruserApplication.deleteNewexamAuthoruser(idList, examid));
		return result;
	}
	
	/**
	 * 查询考试结果
	 * @param examid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectExamResultList")
	public Result selectExamResultList(TNewexamAuthoruser record, int start, int length) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tNewexamAuthoruserApplication.selectExamResultList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
}
