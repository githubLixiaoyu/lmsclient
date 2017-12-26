package com.zxtech.ui.application.ranking.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.ui.application.ranking.TRankingApplication;
import com.zxtech.ui.dao.app.TRankingMapper;

@Named
public class TRankingApplicationImpl implements TRankingApplication{
	@Inject
	private TRankingMapper tRankingMapper;
	
	@Override
	public List<Map<String, Object>> getRankingInfo() throws Exception{
		return tRankingMapper.getRankingInfo();
	}
}
