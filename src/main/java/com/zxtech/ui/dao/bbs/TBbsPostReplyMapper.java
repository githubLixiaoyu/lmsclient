package com.zxtech.ui.dao.bbs;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.bbs.TBbsPostReply;
import com.zxtech.ui.vo.bbs.TBbsPostReplyExample;

public interface TBbsPostReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(TBbsPostReply record);

    int insertSelective(TBbsPostReply record);

    List<TBbsPostReply> selectByExample(TBbsPostReplyExample example);

    TBbsPostReply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TBbsPostReply record);

    int updateByPrimaryKey(TBbsPostReply record);
    
    List<Map<String,Object>> selectBbsReplyInfoByPostId(String postId) ;
    
    List<Map<String,Object>> getCommentByPostParent(String course_id) ;
    
    List<Map<String,Object>> getCommentByPostChild(String course_id) ;
    
    List<Map<String, Object>> selectBbsPostReplyList(TBbsPostReply record);
    
    int deleteBbsPostReply(List<String> ids);
}