package com.zxtech.manager.application.bbs.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.bbs.TBbsTypeApplication;
import com.zxtech.ui.dao.bbs.TBbsTypeMapper;
import com.zxtech.ui.vo.bbs.TBbsType;

@Named
public class TBbsTypeApplicationImpl implements TBbsTypeApplication {
	
	@Inject
	private TBbsTypeMapper tBbsTypeMapper;

	@Override
	public PageInfo<Map<String, Object>> selectBbsTypePageList(TBbsType record, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tBbsTypeMapper.selectTBbsTypeList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	public List<Map<String, Object>> selectBbsTypeList(TBbsType record) throws Exception{
		List<Map<String, Object>> list = tBbsTypeMapper.selectTBbsTypeList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("id"));
			String typeName = String.valueOf(map.get("typeName"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", typeName);
			returnList.add(m);
		}
		return returnList;
	}
}
