package com.zxtech.ui.dao.app;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TApp;

public interface TAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TApp record);

    int insertSelective(TApp record);

    TApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TApp record);

    int updateByPrimaryKey(TApp record);
    
    List<Map<String, Object>> checkNewVersion(String platform);
    
    List<Map<String, Object>> selectAppList(TApp record);
    
    List<Map<String, Object>> selectAppGroupByPlatformList(TApp record);
    
    Map<String, Object> getAndroidApk(String platform);
    
    Map<String, Object> getIosIpa(String platform);
    
    int deleteApp(List<String> ids);
}