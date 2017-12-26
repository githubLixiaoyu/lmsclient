package com.zxtech.teacher.controller.vrnewexam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.esp.vo.vrnewexam.TestInstanceRequest;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.vrnewexam.TestInstanceRequestApplication;


@RestController
@RequestMapping("/teacher/testinstancerequest")
public class TestInstanceRequestController {
	@Autowired
	private TestInstanceRequestApplication testInstanceRequestApplication;

	/**
	 * 查询考试信息
	 * @param record
	 * @param start
	 * @param length
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTestInstanceRequestList")
	public Result selectTestInstanceRequestList(TestInstanceRequest record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = testInstanceRequestApplication.selectTestInstanceRequestList(record, start / length + 1, length);
		result.setMsg("lms0007", new Object[]{page.getList().size()});
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	/**
	 * 保存
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveTestInstanceRequest")
	public Result saveTestInstanceRequest(TestInstanceRequest record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestApplication.saveTestInstanceRequest(record));
		return result;
	}
	
	/**
	 * 禁用
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteTestInstanceRequest")
	public Result deleteTestInstanceRequest(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestApplication.deleteTestInstanceRequest(idList));
		return result;
	}
	
	/**
	 * 发布
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendTestInstanceRequest")
	public Result sendTestInstanceRequest(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestApplication.sendTestInstanceRequest(idList));
		return result;
	}
	
	/**
	 * 根据id查询考试信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTestInstanceRequestById")
	public Result selectTestInstanceRequestById(String id) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestApplication.selectTestInstanceRequestById(id));
		return result;
	}
	
	@RequestMapping("/selectOptionStatusTypeList")
	public Result selectOptionStatusTypeList() throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(testInstanceRequestApplication.selectOptionStatusTypeList());
		return result;
	}
	
}
