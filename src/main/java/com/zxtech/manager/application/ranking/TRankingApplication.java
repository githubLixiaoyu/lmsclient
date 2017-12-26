package com.zxtech.manager.application.ranking;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.app.TRanking;

public interface TRankingApplication {

	//查询
	public PageInfo<Map<String, Object>> selectRankingList(TRanking record, int pageNum, int pageSize) throws Exception;
}
