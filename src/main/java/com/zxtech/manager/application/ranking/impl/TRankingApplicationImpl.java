package com.zxtech.manager.application.ranking.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.ranking.TRankingApplication;
import com.zxtech.ui.dao.app.TRankingMapper;
import com.zxtech.ui.vo.app.TRanking;

@Named
public class TRankingApplicationImpl implements TRankingApplication {
	
	@Inject
	private TRankingMapper tRankingMapper;

	@Override
	public PageInfo<Map<String, Object>> selectRankingList(TRanking record, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		record.setDepartId(AuthDetailUtil.getDepartid());
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tRankingMapper.selectRankingList(record);
		// 封装分页相关信息
		for(int i = 0; i <result.getResult().size();i++){
			result.getResult().get(i).put("rowNum", ((pageNum-1)*20)+ i+1);
		}
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

}
