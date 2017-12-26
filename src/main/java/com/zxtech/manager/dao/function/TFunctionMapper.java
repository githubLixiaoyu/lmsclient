package com.zxtech.manager.dao.function;

import java.util.List;
import java.util.Map;

import com.zxtech.manager.vo.function.TFunction;

public interface TFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFunction record);

    int insertSelective(TFunction record);

    Map<String, Object> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFunction record);

    int updateByPrimaryKey(TFunction record);
    
    List<Map<String, Object>> getFunctionMenu(String authCode);
}