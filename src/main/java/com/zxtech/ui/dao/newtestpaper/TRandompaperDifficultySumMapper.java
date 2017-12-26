package com.zxtech.ui.dao.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySum;
import com.zxtech.ui.vo.newtestpaper.TRandompaperDifficultySumWithBLOBs;

public interface TRandompaperDifficultySumMapper {
    int deleteByPrimaryKey(String id);

    int insert(TRandompaperDifficultySumWithBLOBs record);

    int insertSelective(TRandompaperDifficultySumWithBLOBs record);

    Map<String, Object> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TRandompaperDifficultySumWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TRandompaperDifficultySumWithBLOBs record);

    int updateByPrimaryKey(TRandompaperDifficultySum record);
    
    List<Map<String, Object>> selectRandompaperDifficultySumList(TRandompaperDifficultySum record);
    
    int deleteRandompaperDifficultySum(List<String> ids);
}