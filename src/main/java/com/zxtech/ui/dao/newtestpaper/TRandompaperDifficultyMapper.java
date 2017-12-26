package com.zxtech.ui.dao.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficulty;

public interface TRandompaperDifficultyMapper {
    int deleteByPrimaryKey(String id);

    int insert(TRandompaperDifficulty record);

    int insertSelective(TRandompaperDifficulty record);

    Map<String, Object> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TRandompaperDifficulty record);

    int updateByPrimaryKey(TRandompaperDifficulty record);

	List<Map<String, Object>> selectRandompaperDifficultyList(TRandompaperDifficulty randompaperDifficulty);
	
	List<Map<String, Object>> selectRandompaperDifficultyByPaperidList(Map<String, String> params);
	
	int deleteRandompaperDifficulty(List<String> ids);
	
	int deleteRandompaperDifficultyById(TRandompaperDifficulty randompaperDifficulty);
}