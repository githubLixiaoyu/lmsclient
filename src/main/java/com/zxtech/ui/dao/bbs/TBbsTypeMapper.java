package com.zxtech.ui.dao.bbs;

import com.zxtech.ui.vo.bbs.TBbsType;
import com.zxtech.ui.vo.bbs.TBbsTypeExample;
import java.util.List;
import java.util.Map;

public interface TBbsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBbsType record);

    int insertSelective(TBbsType record);

    List<Map<String, Object>> selectByExample(TBbsTypeExample example);

    List<Map<String, Object>> selectAll();

    TBbsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBbsType record);

    int updateByPrimaryKey(TBbsType record);
    
    List<Map<String, Object>> selectTBbsTypeList(TBbsType record);
}