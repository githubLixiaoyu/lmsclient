package com.zxtech.manager.application.bbs;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.bbs.TBbsPostReply;

public interface TBbsPostReplyApplication {

	//查询
	public PageInfo<Map<String, Object>> selectBbsPostReplyList(TBbsPostReply record, int pageNum, int pageSize) throws Exception;
	//删除
	public int deleteBbsPostReply(String[] idList) throws Exception;
}
