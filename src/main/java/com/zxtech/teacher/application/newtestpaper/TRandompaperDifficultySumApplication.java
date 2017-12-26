package com.zxtech.teacher.application.newtestpaper;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.teacher.vo.newtestpaper.RandompaperDifficultySumVo;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySum;

public interface TRandompaperDifficultySumApplication {
	
	//查询
	public PageInfo<Map<String, Object>> selectRandompaperDifficultySumList(TRandompaperDifficultySum record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveRandompaperDifficultySum(RandompaperDifficultySumVo record) throws Exception;
	//删除
	public int deleteRandompaperDifficultySum(String[] idList, String paperid, String typeid) throws Exception;
	//删除
	public int deleteRandompaperQuestion(TRandompaperDifficultySum record) throws Exception;
//	//发布
//	public int sendRandompaperDifficultySum(String[] idList) throws Exception;
	//查询需要编辑的信息
	public Map<String, Object> selectRandompaperDifficultySumById(TRandompaperDifficultySum record) throws Exception;
}
