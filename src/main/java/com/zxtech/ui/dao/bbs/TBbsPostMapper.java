package com.zxtech.ui.dao.bbs;

import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostExample;
import java.util.List;
import java.util.Map;

public interface TBbsPostMapper {
    int deleteByPrimaryKey(String id);

    int insert(TBbsPost record);

    int insertSelective(TBbsPost record);

    List<Map<String, Object>> selectByExample(TBbsPostExample example);

    TBbsPost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TBbsPost record);

    int updateByPrimaryKey(TBbsPost record);

    int addBbsLookNum(String id);
    
    List<Map<String, Object>> selectBbsInfo();
    
    List<Map<String, Object>> selectBbsPost();
    
    List<Map<String, Object>> selectBbsPostList(TBbsPost record);
    
    int deleteBbsPost(List<String> ids);
}