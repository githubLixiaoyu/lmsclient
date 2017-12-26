package com.zxtech.ui.application.course.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.ui.application.course.TCourseTypeApplication;
import com.zxtech.ui.dao.app.TCourseTypeMapper;
import com.zxtech.ui.vo.app.TCourseType;

@Named
public class TCourseTypeApplicationImpl implements TCourseTypeApplication{
	@Inject
	private TCourseTypeMapper tCourseTypeMapper;
	@Override
	public Map<String, Object> selectAll(HttpServletRequest request, TCourseType tCourseType) throws Exception{
		List<Map<String, Object>> list = tCourseTypeMapper.selectAll(tCourseType);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listSub = null;
		Map<String, Object> mapSub = null;
		String parentidSub = "";
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String parentid  = String.valueOf(m.get("parentid"));
				if (!StringUtils.equals(parentidSub, parentid)) {
					listSub = new ArrayList<Map<String, Object>>();
					map.put(parentid, listSub);
				}
				mapSub = new HashMap<String, Object>();
				listSub.add(mapSub);
				mapSub.put("id", m.get("id"));
				mapSub.put("coursetypename", m.get("coursetypename"));
				mapSub.put("coursetypenameEn", m.get("coursetypenameEn"));
				mapSub.put("imagename", m.get("imagename"));
				
				parentidSub = parentid;
			}
		}

		String showPath = "http://" + request.getServerName();
		if (!"80".equals(request.getServerPort())) {
			showPath += ":" + request.getServerPort();
		}
		showPath += "/lmsFiles/courseTypeImages/";
		
		String userId = AuthDetailUtil.getLoginUserId();
		
		map.put("path", showPath);
		map.put("userId", userId);
		
		return map;
	}
	@Override
	public Map<String, Object> selectAllByUserId(TCourseType tCourseType) throws Exception{
		
		String userId = AuthDetailUtil.getLoginUserId();
		tCourseType.setUserId(userId);
		String photoName = AuthDetailUtil.getPhotoName();
		List<Map<String, Object>> list = tCourseTypeMapper.selectAllByUserId(tCourseType);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listSub = null;
		Map<String, Object> mapSub = null;
		String parentidSub = "";
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String parentid  = String.valueOf(m.get("parentid"));
				if (!StringUtils.equals(parentidSub, parentid)) {
					listSub = new ArrayList<Map<String, Object>>();
					map.put(parentid, listSub);
				}
				mapSub = new HashMap<String, Object>();
				listSub.add(mapSub);
				mapSub.put("id", m.get("id"));
				mapSub.put("coursetypename", m.get("coursetypename"));
				mapSub.put("coursetypenameEn", m.get("coursetypenameEn"));
				mapSub.put("imagename", m.get("imagename"));
				mapSub.put("count", m.get("count"));
				
				parentidSub = parentid;
			}
		}
		map.put("userId", userId);
		map.put("photoName", photoName);
		
		return map;
	}
}
