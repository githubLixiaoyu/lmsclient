package com.zxtech.teacher.application.newtestpaper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zxtech.teacher.application.newtestpaper.TRandompaperQuestiontypeApplication;
import com.zxtech.ui.dao.newtestpaper.TRandompaperQuestiontypeMapper;
import com.zxtech.ui.dao.question.TQuestionstypeMapper;
import com.zxtech.ui.vo.newtestpaper.TRandompaperQuestiontype;

@Named
public class TRandompaperQuestiontypeApplicationImpl implements TRandompaperQuestiontypeApplication{
	@Inject
	private TRandompaperQuestiontypeMapper tRandompaperQuestiontypeMapper;
	@Inject
	private TQuestionstypeMapper tQuestionstypeMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Map<String, Object>> selectRandompaperQuestiontype(TRandompaperQuestiontype record) throws Exception {
		List<Map<String, Object>> typeList = tQuestionstypeMapper.selectQuestionstypeList(null);
		List<Map<String, Object>> paperList = tRandompaperQuestiontypeMapper.selectRandompaperQuestiontype(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		boolean b = false;
		if (typeList != null && typeList.size() > 0) {
			for (Map<String, Object> typeMap : typeList) {
				b = false;
				String typeId = String.valueOf(typeMap.get("typeid"));
				if (paperList != null && paperList.size() > 0) {
					for (Map<String, Object> paperMap : paperList) {
						String paperTypeId = String.valueOf(paperMap.get("typeid"));
						if (typeId.equals(paperTypeId)) {
							b = true;
							returnList.add(paperMap);
							break;
						}
					}
				}
				if (!b) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("paperid", record.getPaperid());
					m.put("typeid", typeMap.get("typeid"));
					m.put("name", typeMap.get("name"));
					m.put("display", "1");
					m.put("checkstatus", "0");
					m.put("totlequestions", 0);
					m.put("titlescore", 0);
					returnList.add(m);
					if(record.getPaperid() != null){
						TRandompaperQuestiontype tRandompaperQuestiontype = new TRandompaperQuestiontype();
						tRandompaperQuestiontype.setPaperid(record.getPaperid());
						tRandompaperQuestiontype.setTypeid(String.valueOf(typeMap.get("typeid")));
						tRandompaperQuestiontype.setDisplay("1");
						tRandompaperQuestiontype.setCheckstatus("0");
						tRandompaperQuestiontype.setTotlequestions(0d);
						tRandompaperQuestiontype.setTitlescore(0d);
						tRandompaperQuestiontypeMapper.insertSelective(tRandompaperQuestiontype);
					}
				}
			}
		}
		return returnList;
	}
}
