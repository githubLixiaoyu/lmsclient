package com.zxtech.manager.application.question.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.question.TKnowledgepointApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.ui.dao.question.TKnowledgepointMapper;
import com.zxtech.ui.vo.question.TKnowledgepoint;

@Named
public class TKnowledgepointApplicationImpl implements TKnowledgepointApplication{
	@Inject
	private TKnowledgepointMapper tKnowledgepointMapper;

	@Override
	public PageInfo<Map<String, Object>> selectKnowledgepointList(TKnowledgepoint record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = tKnowledgepointMapper.selectKnowledgepointList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveKnowledgepoint(TKnowledgepoint record) throws Exception{
		try{
			if(record.getPoint() != null && !"".equals(record.getPoint())){
				return tKnowledgepointMapper.updateByPrimaryKeySelective(record);
			}else{
				UUID uuid = UUID.randomUUID();
				record.setPoint(uuid.toString());
				record.setStandby1("0");
				tKnowledgepointMapper.insertSelective(record);
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteKnowledgepoint(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tKnowledgepointMapper.deleteKnowledgepoint(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

	@Override
	public List<Map<String, Object>> selectOptionKnowledgepointList(TKnowledgepoint record) throws Exception{
		List<Map<String, Object>> list = tKnowledgepointMapper.selectKnowledgepointList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("point"));
			String text = String.valueOf(map.get("pointname"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", text);
			returnList.add(m);
		}
		return returnList;
	}
		
}
