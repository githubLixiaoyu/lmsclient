package com.zxtech.ui.dao.bbs;

import com.zxtech.ui.vo.bbs.TBbsPostImage;
import com.zxtech.ui.vo.bbs.TBbsPostImageExample;
import java.util.List;
import java.util.Map;

public interface TBbsPostImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TBbsPostImage record);

    int insertSelective(TBbsPostImage record);

    List<Map<String, Object>> selectByExample(TBbsPostImageExample example);

    TBbsPostImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TBbsPostImage record);

    int updateByPrimaryKey(TBbsPostImage record);
}