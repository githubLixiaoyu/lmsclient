package com.zxtech.manager.application.app;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.app.TApp;

public interface TAppApplication {

	//查询
	public PageInfo<Map<String, Object>> selectAppList(TApp record, int pageNum, int pageSize) throws Exception;
	
	public PageInfo<Map<String, Object>> selectAppGroupByPlatformList(TApp record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveApp(TApp record) throws Exception;
	//删除
	public int deleteApp(String[] idList) throws Exception;
}
