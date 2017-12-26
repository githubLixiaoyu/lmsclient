package com.zxtech.esp.dao.vrnewtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewtestpaper.TestTemplate;



public interface TestTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestTemplate record);

    int insertSelective(TestTemplate record);

    TestTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestTemplate record);

    int updateByPrimaryKey(TestTemplate record);
    
    List<Map<String, Object>> selectVRTestTemplateList(TestTemplate record);
}