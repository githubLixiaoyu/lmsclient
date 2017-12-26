package com.zxtech.esp.dao.vrnewtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewtestpaper.TestTemplateOperation;

public interface TestTemplateOperationMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TestTemplateOperation record);

	int insertSelective(TestTemplateOperation record);

	TestTemplateOperation selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TestTemplateOperation record);

	int updateByPrimaryKey(TestTemplateOperation record);

	// 根据ID 查询
	List<Map<String, Object>> selectTestTemplateOperationByPaperId(long paperId);

	// 根据ID 查询MilestoneId
	List<Map<String, Object>> selectTestTemplateOperationById(long id);

}