package com.zxtech.manager.application.newtestpaper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperCategory;

public interface TNewtestpaperCategoryApplication {
	//查询
	public PageInfo<Map<String, Object>> selectNewtestpaperCategoryList(TNewtestpaperCategory record, int pageNum, int pageSize) throws Exception;
	//查询
	public List<Map<String, Object>> selectNewtestpaperCategoryList(TNewtestpaperCategory record) throws Exception;
	//保存
	public int saveNewtestpaperCategory(TNewtestpaperCategory record) throws Exception;
	//删除
	public int deleteNewtestpaperCategory(String[] idList) throws Exception;
}
