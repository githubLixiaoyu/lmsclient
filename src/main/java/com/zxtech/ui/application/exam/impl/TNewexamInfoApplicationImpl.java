package com.zxtech.ui.application.exam.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.esp.dao.vrnewexam.TestInstanceRequestMapper;
import com.zxtech.support.util.Util;
import com.zxtech.ui.application.exam.TNewexamInfoApplication;
import com.zxtech.ui.dao.newexam.TNewExamInfoMapper;
import com.zxtech.ui.vo.newexam.TNewExamInfo;

@Named
public class TNewexamInfoApplicationImpl implements TNewexamInfoApplication{
	@Inject
	private TNewExamInfoMapper tNewExamInfoMapper;
	@Inject
	private TestInstanceRequestMapper testInstanceRequestMapper;
	@Override
	public Map<String, Object> selectExamInfoByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = tNewExamInfoMapper.selectExamInfoByFilter(tNewExamInfo);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String startTime = String.valueOf(m.get("starttime"));
				String endTime = String.valueOf(m.get("endtime"));
				if (!"".equals(startTime)) {
					Date dts = df.parse(startTime);
					Date dte = df.parse(endTime);
					Date dtn = df.parse(df.format(new Date()));
					if (dts.getTime() > dtn.getTime()) {
						m.put("flag", "before");
					} else if (dtn.getTime() > dte.getTime()) {
						m.put("flag", "after");
					} else {
						m.put("flag", "now");
					}
				} else {
					m.put("flag", "now");
				}
			}
		}
		Map<String, Object> noExamMap = tNewExamInfoMapper.getNoExamNum(tNewExamInfo);
		Map<String, Object> examedMap = tNewExamInfoMapper.getExamedNum(tNewExamInfo);
		map.put("data", list);
		map.put("noExamNum", noExamMap.get("count"));
		map.put("examedNum", examedMap.get("count"));

		return map;
	}
	
	@Override
	public Map<String, Object> selectPcExamInfoByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		List<Map<String, Object>> list = tNewExamInfoMapper.selectExamInfoByFilter(tNewExamInfo);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> m : list) {
				String startTime = String.valueOf(m.get("starttime"));
				String endTime = String.valueOf(m.get("endtime"));
				if (!"".equals(startTime)) {
					Date dts = df.parse(startTime);
					Date dte = df.parse(endTime);
					Date dtn = df.parse(df.format(new Date()));
					if (dts.getTime() > dtn.getTime()) {
						m.put("flag", "before");
					} else if (dtn.getTime() > dte.getTime()) {
						m.put("flag", "after");
					} else {
						m.put("flag", "now");
					}
				} else {
					m.put("flag", "now");
				}
				m.put("isVr", 0);
			}
		}
		//VR 考试信息查询
		List<Map<String, Object>> vrlist = testInstanceRequestMapper.selectVrExamInfoByFilter(tNewExamInfo);
		if (vrlist != null && vrlist.size() > 0) {
			for (Map<String, Object> m : vrlist) {
				String startTime = String.valueOf(m.get("starttime"));
				String endTime = String.valueOf(m.get("endtime"));
				if (!"".equals(startTime)) {
					Date dts = df.parse(startTime);
					Date dte = df.parse(endTime);
					Date dtn = df.parse(df.format(new Date()));
					if (dts.getTime() > dtn.getTime()) {
						m.put("flag", "before");
					} else if (dtn.getTime() > dte.getTime()) {
						m.put("flag", "after");
					} else {
						m.put("flag", "now");
					}
				} else {
					m.put("flag", "now");
				}
				m.put("isVr", 1);
			}
		}
		List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			listTemp.addAll(list);
		}
		if (vrlist != null && vrlist.size() > 0) {
			listTemp.addAll(vrlist);
		}
		//根据时间排序
		Util.quickSortByDate(listTemp,"createDate",0,list.size()-1);
		
		Map<String, Object> theoryNoExamMap = tNewExamInfoMapper.getNoExamNum(tNewExamInfo);
		Map<String, Object> theoryExamedMap = tNewExamInfoMapper.getExamedNum(tNewExamInfo);
		
		//获取vr未考数量
		Map<String, Object> vrNoExamMap = testInstanceRequestMapper.getVrNoExamNum(tNewExamInfo);
		//获取vr已考数量
		Map<String, Object> vrExamedMap = testInstanceRequestMapper.getVrExamedNum(tNewExamInfo);
		
		map.put("data", listTemp);
		//未考总数和
		map.put("noExamNum", Integer.valueOf(String.valueOf(theoryNoExamMap.get("count"))).intValue() + Integer.valueOf(String.valueOf(vrNoExamMap.get("count"))).intValue());
		//已考总数和
		map.put("examedNum", Integer.valueOf(String.valueOf(theoryExamedMap.get("count"))).intValue() + Integer.valueOf(String.valueOf(vrExamedMap.get("count"))).intValue());
		
		return map;
		
	}
	
	@Override
	public Map<String, Object> getUserExamInfo(TNewExamInfo tNewExamInfo) throws Exception{
		if ("0".equals(tNewExamInfo.getIsVr())) {
			return tNewExamInfoMapper.getUserExamInfo(tNewExamInfo);
		} else {
			// VR
			Map<String, Object> map = testInstanceRequestMapper.getVrUserExamInfo(tNewExamInfo);
			String espPath = Util.readPropertiesValue("EspPath");
			map.put("espPath", espPath);
			return map;
		}
	}
}
