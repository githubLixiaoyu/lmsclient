package com.zxtech.ui.dao.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;

public interface TNewTestpaperInfoMapper {
    int deleteByPrimaryKey(String paperid);

    int insert(TNewTestpaperInfo record);

    int insertSelective(TNewTestpaperInfo record);

    Map<String, Object> selectByPrimaryKey(String paperid);

    Map<String, Object> getNewtestpaperInfo(TNewTestpaperInfo record);

    int updateByPrimaryKeySelective(TNewTestpaperInfo record);

    int updateByPrimaryKey(TNewTestpaperInfo record);
    
    List<Map<String, Object>> selectNewTestpaperInfoList(TNewTestpaperInfo record);
    
    int sendNewTestpaperInfo(List<String> ids);
}