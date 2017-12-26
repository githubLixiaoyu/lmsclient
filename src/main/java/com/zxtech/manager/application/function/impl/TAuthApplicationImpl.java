package com.zxtech.manager.application.function.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.manager.application.function.TAuthApplication;
import com.zxtech.manager.dao.function.TAuthMapper;
import com.zxtech.manager.vo.function.TAuth;

@Named
public class TAuthApplicationImpl implements TAuthApplication {
	
	@Inject
	private TAuthMapper tAuthMapper;

	@Override
	public List<Map<String, Object>> selectOptionAuthList(TAuth record) throws Exception{
		List<Map<String, Object>> list = tAuthMapper.selectAuthList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				String id = String.valueOf(map.get("id"));
				String text = String.valueOf(map.get("authName"));
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", id);
				m.put("text", text);
				returnList.add(m);
			}
		}
		return returnList;
	}

}
