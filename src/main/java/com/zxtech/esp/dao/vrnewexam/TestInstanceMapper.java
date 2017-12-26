package com.zxtech.esp.dao.vrnewexam;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewexam.TestInstance;

public interface TestInstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestInstance record);

    int insertSelective(TestInstance record);

    TestInstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestInstance record);

    int updateByPrimaryKey(TestInstance record);
    
    List<Map<String, Object>> selectTestInstanceList(TestInstance record);
}