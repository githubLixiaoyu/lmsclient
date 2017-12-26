package com.zxtech.manager.application.bbs;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.bbs.TBbsType;

public interface TBbsTypeApplication {

	//查询
	public PageInfo<Map<String, Object>> selectBbsTypePageList(TBbsType record, int pageNum, int pageSize) throws Exception;
	
	public List<Map<String, Object>> selectBbsTypeList(TBbsType record) throws Exception;
}
