package com.zxtech.teacher.controller.vrnewexam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.esp.vo.vrnewexam.TestInstanceRequestUsers;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.vrnewexam.TestInstanceRequestUsersApplication;


@RestController
@RequestMapping("/teacher/testinstancerequestusers")
public class TestInstanceRequestUsersController {
	@Autowired
	private TestInstanceRequestUsersApplication testInstanceRequestUsersApplication;

	/**
	 * 查询已添加人员
	 * @param record
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTestInstanceRequestUsersList")
	public Result selectTestInstanceRequestUsersList(TestInstanceRequestUsers record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = testInstanceRequestUsersApplication.selectTestInstanceRequestUsersList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	/**
	 * 查询可选择人员
	 * @param record
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectCanSelectForTestInstanceRequestList")
	public Result selectCanSelectForTestInstanceRequestList(TestInstanceRequestUsers record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = testInstanceRequestUsersApplication.selectCanSelectForTestInstanceRequestList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	/**
	 * 新增人员
	 * @param idList
	 * @param testInstanceRequestId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveTestInstanceRequestUsers")
	public Result saveTestInstanceRequestUsers(@RequestParam(value = "idList[]") String[] idList, String testInstanceRequestId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestUsersApplication.saveTestInstanceRequestUsers(idList, testInstanceRequestId));
		return result;
	}
	
	/**
	 * 删除人员
	 * @param idList
	 * @param testInstanceRequestId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteTestInstanceRequestUsers")
	public Result deleteTestInstanceRequestUsers(@RequestParam(value = "idList[]") String[] idList, String testInstanceRequestId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestUsersApplication.deleteTestInstanceRequestUsers(idList, testInstanceRequestId));
		return result;
	}
	
	/**
	 * 查询考试结果
	 * @param examid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectExamResultList")
	public Result selectExamResultList(TestInstanceRequestUsers record, int start, int length) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = testInstanceRequestUsersApplication.selectExamResultList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
}
