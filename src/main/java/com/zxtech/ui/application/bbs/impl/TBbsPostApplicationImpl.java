package com.zxtech.ui.application.bbs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.auth.CustomUserDetails;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.application.bbs.TBbsPostApplication;
import com.zxtech.ui.dao.bbs.TBbsPostMapper;
import com.zxtech.ui.dao.bbs.TBbsPostReplyMapper;
import com.zxtech.ui.dao.bbs.TBbsTypeMapper;
import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostReply;

@Named
public class TBbsPostApplicationImpl implements TBbsPostApplication{
	@Inject
	private TBbsPostMapper tBbsPostMapper;
	@Inject
	private TBbsTypeMapper tBbsTypeMapper;
	@Inject
	private TBbsPostReplyMapper tBbsPostReplyMapper;
	
	@Override
	public Map<String, Object> selectBbsInfo() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		CustomUserDetails userDetails = AuthDetailUtil.getLoginInfo();
		String userId = "";
		String nickName = "";
		String photoName = "";
		if (userDetails != null) {
			userId = userDetails.getUserId();
			nickName = userDetails.getNickName();
			photoName = userDetails.getPhotoName();
		}
		map.put("userId", userId);
		map.put("nickName", nickName);
		map.put("photoName", photoName);
		
		List<Map<String, Object>> list = tBbsTypeMapper.selectAll();
		if (list != null && list.size() > 0) {
			map.put("bbsTypeList", list);
		} else {
			map.put("bbsTypeList", new ArrayList<Map<String, Object>>());
		}
		
		List<Map<String, Object>> postList = tBbsPostMapper.selectBbsInfo();
		if (postList != null && postList.size() > 0) {
			map.put("postList", postList);
		} else {
			map.put("postList", new ArrayList<Map<String, Object>>());
		}
		
		return map;
	}
	
	@Override
	public int saveBbsInfo(TBbsPost tBbsPost) throws Exception{
		tBbsPost.setId(Util.getUUID());
		tBbsPost.setCreateDate(new Date());
		tBbsPost.setEnableFlag("1");
		tBbsPost.setLookNum(0);
		return tBbsPostMapper.insertSelective(tBbsPost);
	}
	
	@Override
	public List<Map<String, Object>> selectBbsReplyInfoByPostId(String postId) throws Exception{
		return tBbsPostReplyMapper.selectBbsReplyInfoByPostId(postId);
	}
	
	@Override
	public int addBbsLookNum(String id) throws Exception{
		return tBbsPostMapper.addBbsLookNum(id);
	}
	
	@Override
	public int addBbsReply(TBbsPostReply tBbsPostReply) throws Exception{
		tBbsPostReply.setId(Util.getUUID());
		tBbsPostReply.setCreateDate(new Date());
		tBbsPostReply.setEnableFlag("1");
		return tBbsPostReplyMapper.insertSelective(tBbsPostReply);
	}
}
