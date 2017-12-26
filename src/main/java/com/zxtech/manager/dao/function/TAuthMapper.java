package com.zxtech.manager.dao.function;

import java.util.List;
import java.util.Map;

import com.zxtech.manager.vo.function.TAuth;

public interface TAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuth record);

    int insertSelective(TAuth record);

    Map<String, Object> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuth record);

    int updateByPrimaryKey(TAuth record);
    
    List<Map<String, Object>> selectAuthList(TAuth record);
}