package com.zxtech.ui.application.ranking;

import java.util.List;
import java.util.Map;

public interface TRankingApplication {
	
	List<Map<String, Object>> getRankingInfo() throws Exception;
}
