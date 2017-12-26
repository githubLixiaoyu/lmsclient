package com.zxtech.manager.application.permissions;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.permissions.TLoginInfo;

public interface TLoginInfoApplication {
	//查询
	public PageInfo<Map<String, Object>> selectLoginInfoList(TLoginInfo record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveLoginInfo() throws Exception;
}
