package com.zxtech.ui.dao.app;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TRanking;

public interface TRankingMapper {
    int deleteByPrimaryKey(String id);

    int insert(TRanking record);

    int insertSelective(TRanking record);

    Map<String,Object> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TRanking record);

    int updateByPrimaryKey(TRanking record);
    
    List<Map<String,Object>> selectRankingAll();
    
    List<Map<String,Object>> getRankingInfo();
    
    Map<String,Object> selectRankByUser(String userId);
    
    List<Map<String, Object>> selectRankingList(TRanking record);
}