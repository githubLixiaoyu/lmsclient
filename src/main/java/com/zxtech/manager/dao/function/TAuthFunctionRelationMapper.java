package com.zxtech.manager.dao.function;

import java.util.Map;

import com.zxtech.manager.vo.function.TAuthFunctionRelation;

public interface TAuthFunctionRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuthFunctionRelation record);

    int insertSelective(TAuthFunctionRelation record);

    Map<String, Object> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuthFunctionRelation record);

    int updateByPrimaryKey(TAuthFunctionRelation record);
}