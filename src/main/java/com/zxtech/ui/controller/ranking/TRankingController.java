package com.zxtech.ui.controller.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxtech.support.controller.Result;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.ui.application.ranking.TRankingApplication;

@RestController
@RequestMapping("/ui/tranking")
public class TRankingController {
	@Autowired
	private TRankingApplication tRankingApplication;

	@RequestMapping("/getRankingInfo")
	public Result getRankingInfo() throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tRankingApplication.getRankingInfo());
		return result;
	}
}
