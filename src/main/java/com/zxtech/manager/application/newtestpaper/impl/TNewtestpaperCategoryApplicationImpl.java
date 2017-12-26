package com.zxtech.manager.application.newtestpaper.impl;

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
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.newtestpaper.TNewtestpaperCategoryApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.ui.dao.newtestpaper.TNewtestpaperCategoryMapper;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperCategory;

@Named
public class TNewtestpaperCategoryApplicationImpl implements TNewtestpaperCategoryApplication{
	@Inject
	private TNewtestpaperCategoryMapper tNewtestpaperCategoryMapper;

	@Override
	public PageInfo<Map<String, Object>> selectNewtestpaperCategoryList(TNewtestpaperCategory record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tNewtestpaperCategoryMapper.selectNewtestpaperCategoryList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	public List<Map<String, Object>> selectNewtestpaperCategoryList(TNewtestpaperCategory record) throws Exception{
		List<Map<String, Object>> list = tNewtestpaperCategoryMapper.selectNewtestpaperCategoryList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				String id = String.valueOf(map.get("pointsid"));
				String text = String.valueOf(map.get("pointsname"));
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", id);
				m.put("text", text);
				returnList.add(m);
			}
		}
		return returnList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveNewtestpaperCategory(TNewtestpaperCategory record) throws Exception{
		try{
			if(record.getPointsid() != null && !"".equals(record.getPointsid())){
				return tNewtestpaperCategoryMapper.updateByPrimaryKeySelective(record);
			}else{
				UUID uuid = UUID.randomUUID();
				record.setPointsid(uuid.toString());
				record.setStandby1("0");
				record.setCreater(AuthDetailUtil.getLoginUserId());
				tNewtestpaperCategoryMapper.insertSelective(record);
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteNewtestpaperCategory(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tNewtestpaperCategoryMapper.deleteNewtestpaperCategory(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除人员失败!");
		}
	}
		
}
