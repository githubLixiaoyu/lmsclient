package com.zxtech.ui.dao.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newtestpaper.TNewtestpaperCategory;

public interface TNewtestpaperCategoryMapper {
    int deleteByPrimaryKey(String pointsid);

    int insert(TNewtestpaperCategory record);

    int insertSelective(TNewtestpaperCategory record);

    TNewtestpaperCategory selectByPrimaryKey(String pointsid);

    int updateByPrimaryKeySelective(TNewtestpaperCategory record);

    int updateByPrimaryKeyWithBLOBs(TNewtestpaperCategory record);

    int updateByPrimaryKey(TNewtestpaperCategory record);
    
    List<Map<String, Object>> selectNewtestpaperCategoryList(TNewtestpaperCategory record);
    
    int deleteNewtestpaperCategory(List<String> ids);
}