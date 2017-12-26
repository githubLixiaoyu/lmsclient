package com.zxtech.ui.dao.question;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.question.TKnowledgepoint;

public interface TKnowledgepointMapper {
    int deleteByPrimaryKey(String point);

    int insert(TKnowledgepoint record);

    int insertSelective(TKnowledgepoint record);

    TKnowledgepoint selectByPrimaryKey(String point);

    int updateByPrimaryKeySelective(TKnowledgepoint record);

    int updateByPrimaryKeyWithBLOBs(TKnowledgepoint record);

    int updateByPrimaryKey(TKnowledgepoint record);
    
    List<Map<String, Object>> selectKnowledgepointList(TKnowledgepoint record);
    
    int deleteKnowledgepoint(List<String> ids);
}