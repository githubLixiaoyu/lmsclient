package com.zxtech.manager.application.bbs;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.bbs.TBbsPost;

public interface TBbsPostApplication {

	//查询
	public PageInfo<Map<String, Object>> selectBbsPostList(TBbsPost record, int pageNum, int pageSize) throws Exception;
	//删除
	public int deleteBbsPost(String[] idList) throws Exception;
}
