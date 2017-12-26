package com.zxtech.teacher.controller.vrnewtestpaper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplate;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplateMilestone;
import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.teacher.application.vrnewtestpaper.TVRNewTestpaperInfoApplication;

@RestController
@RequestMapping("/teacher/tvrnewtestpaper")
public class TVRNewTestpaperInfoController {

	@Autowired
	private TVRNewTestpaperInfoApplication tVRNewTestpaperInfoApplication;

	/**
	 * 获取试卷信息
	 * 
	 * @param record
	 * @param start
	 * @param length
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/selectVRNewTestpaperInfoList")
	public Result selectNewTestpaperInfoList(TestTemplateMilestone record, int start, int length) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		PageInfo<Map<String, Object>> page = tVRNewTestpaperInfoApplication.selectTestTemplateMilestoneList(record,
				start / length + 1, length);
		List<Map<String, Object>> userlist = tVRNewTestpaperInfoApplication.selectAllUserList();
		List<Map<String, Object>> testtemplatemilestonelist = page.getList();
		for (Map<String, Object> map1 : testtemplatemilestonelist) {
			for (Map<String, Object> map2 : userlist) {				
				if (map1.get("requestUserId").equals(map2.get("studentid"))) {
					map1.put("logincode", map2.get("logincode"));
				}
			}
		}
		result.setMsg("lms0007", new Object[] { page.getList().size() });
		result.setData(testtemplatemilestonelist);
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}

	/**
	 * 获取试卷信息ById
	 * 
	 * @param paperId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectVRNewTestpaperInfoListById")
	public Result selectVRNewTestpaperInfoListById(long paperId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		List<Map<String, Object>> list = tVRNewTestpaperInfoApplication.selectTestTemplateMilestoneListById(paperId);
		result.setMsg("lms0002");
		result.setData(list);
		return result;
	}

	/**
	 * 添加试卷信息
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveTestTemplateMilestone")
	public Result saveCourseType(TestTemplateMilestone record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tVRNewTestpaperInfoApplication.saveTestTemplateMilestone(record));
		return result;
	}

	/**
	 * 考试类别
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectVRTestTemplateList")
	public Result selectVRTestTemplateList(TestTemplate record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tVRNewTestpaperInfoApplication.selectVRTestTemplateList(record);
		result.setData(list);
		return result;
	}

	/**
	 * 修改试卷状态
	 * 
	 * @param idList
	 * @param statusTypeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updataTestStatusType")
	public Result updataTestStatusType(String[] idList, int statusTypeId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tVRNewTestpaperInfoApplication.updateTestStatusType(idList, statusTypeId));
		return result;
	}

	/**
	 * 根据课程获取知识点
	 * 
	 * @param paperId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTestPaperOperationByMilestoneId")
	public Result selectTestPaperOperationByMilestoneId(long paperId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tVRNewTestpaperInfoApplication.selectTestTemplateOperationByPaperId(paperId));
		return result;
	}

	/**
	 * 保存课程知识点
	 * 
	 * @param checkNameId
	 * @param checkSequence
	 * @param checkSorce
	 * @param paperId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveTestPaperOperation")
	public Result saveTestPaperOperation(long[] checkNameId, long[] checkSequence, long[] checkSorce, long paperId)
			throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		int num = tVRNewTestpaperInfoApplication.saveTestPaperOperation(paperId, checkNameId, checkSequence,
				checkSorce);
		result.setMsg("lms0002");
		result.setData(num);
		return result;
	}

	/**
	 * 获取对应试卷ID的课程知识点
	 * 
	 * @param mileStoneId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTPaperOperation")
	public Result selectTPaperOperation(long mileStoneId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		List<Map<String, Object>> list = tVRNewTestpaperInfoApplication.selectTPaperOperation(mileStoneId);
		result.setMsg("lms0002");
		result.setData(list);
		return result;
	}

	@RequestMapping("/selectTestPaperInfo")
	public Result selectTestPaperInfo(long mileStoneId) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		List<Map<String, Object>> list = tVRNewTestpaperInfoApplication.selectTestPaperInfo(mileStoneId);
		result.setMsg("lms0002");
		result.setData(list);
		return result;
	}
}
