package com.zxtech.ui.application.bbs;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostReply;

public interface TBbsPostApplication {
	
	Map<String, Object> selectBbsInfo() throws Exception;
	
	int saveBbsInfo(TBbsPost tBbsPost) throws Exception;
	
	List<Map<String, Object>> selectBbsReplyInfoByPostId(String postId) throws Exception;
	
	int addBbsLookNum(String id) throws Exception;
	
	int addBbsReply(TBbsPostReply tBbsPostReply) throws Exception;
}
