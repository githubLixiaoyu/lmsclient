package com.zxtech.ui.dao.newexam;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewexamImage;

public interface TNewexamImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TNewexamImage record);

    int insertSelective(TNewexamImage record);

    TNewexamImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TNewexamImage record);

    int updateByPrimaryKey(TNewexamImage record);
    
    List<Map<String, Object>> selectNewexamImageList(TNewexamImage record);
    
    int deleteByExamid(String examid);
    
    int deleteNewexamImage(List<String> ids);
}