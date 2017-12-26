package com.zxtech.esp.dao.vrnewtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewtestpaper.TestPaperOperation;

public interface TestPaperOperationMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TestPaperOperation record);

	int insertSelective(TestPaperOperation record);

    Map<String, Object> selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TestPaperOperation record);

	int updateByPrimaryKey(TestPaperOperation record);

	// 根据milestoneId 删除知识点
	int deleteTestPaperOperationByMilestoneId(long mileStoneId);

	// 根据milestoneId 查找知识点
	List<Map<String, Object>> selectTestTemplateOperationBymileStoneId(long mileStoneId);

	// 保存知识点
	int saveTestPaperOperation(TestPaperOperation record);

    
    int insertTestPaperOperation(Map<String, Object> params);
    
    List<Map<String, Object>> selectTestPaperOperationList(TestPaperOperation record);
    
    List<Map<String, Object>> selectTestPaperInfo(long mileStoneId);
}