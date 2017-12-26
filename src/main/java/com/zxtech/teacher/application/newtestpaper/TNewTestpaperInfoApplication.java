package com.zxtech.teacher.application.newtestpaper;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;

public interface TNewTestpaperInfoApplication {
	
	public Map<String, Object> addNewtestpaperInfo(TNewTestpaperInfo record) throws Exception;
	
	public Map<String, Object> updateNewtestpaperInfo(TNewTestpaperInfo record) throws Exception;
	
	public Map<String, Object> getNewtestpaperInfo(TNewTestpaperInfo record) throws Exception;

	//查询
	public PageInfo<Map<String, Object>> selectNewTestpaperInfoList(TNewTestpaperInfo record, int pageNum, int pageSize) throws Exception;
	//发布
	public int sendNewTestpaperInfo(String[] idList) throws Exception;
}
