package com.zxtech.teacher.application.vrnewtestpaper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplate;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplateMilestone;

public interface TVRNewTestpaperInfoApplication {

	// 查询
	public PageInfo<Map<String, Object>> selectTestTemplateMilestoneList(TestTemplateMilestone record, int pageNum,
			int pageSize) throws Exception;

	// 查询
	public List<Map<String, Object>> selectTestTemplateMilestoneListById(long paperId) throws Exception;

	// 保存
	public int saveTestTemplateMilestone(TestTemplateMilestone record) throws Exception;

	// 查询
	public List<Map<String, Object>> selectVRTestTemplateList(TestTemplate record) throws Exception;

	// 查询所有User
	public List<Map<String, Object>> selectAllUserList() throws Exception;

	// 修改考试试卷状态
	public int updateTestStatusType(String[] idList, int statusTypeId) throws Exception;

	//根据试卷ID，试卷类别  查询试卷点
	public List<Map<String, Object>> selectTestTemplateOperationByPaperId(long paperId) throws Exception;
	
	// 保存试卷知识点
	public int saveTestPaperOperation(long paperId,long[] checkNameId,long[] checkSequence,long[] checkSorce) throws Exception;

    //根据milestone_id 查询考试点
	public List<Map<String, Object>> selectTPaperOperation(long mileStoneId) throws Exception;

	//查询试卷考试点
	public List<Map<String, Object>> selectTestPaperInfo(long mileStoneId) throws Exception;


}
