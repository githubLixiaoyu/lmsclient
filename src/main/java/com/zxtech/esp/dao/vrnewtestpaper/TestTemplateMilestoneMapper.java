package com.zxtech.esp.dao.vrnewtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewtestpaper.TestTemplateMilestone;

public interface TestTemplateMilestoneMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TestTemplateMilestone record);
	
	int insertTestTemplateMilestone(Map<String, Object> params);

	int insertSelective(TestTemplateMilestone record);

	Map<String, Object> selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TestTemplateMilestone record);

	int updateByPrimaryKey(TestTemplateMilestone record);

	// 查询所有
	List<Map<String, Object>> selectTestTemplateMilestoneList(TestTemplateMilestone record);

	// 修改试卷状态，发布
	int updateTestStatusType(List<String> ids);

	// 修改试卷状态，删除
	int deleteTestStatusType(List<String> ids);

	// 根据ID 查询
	List<Map<String, Object>> selectTestTemplateMilestoneListById(long paperId);

}