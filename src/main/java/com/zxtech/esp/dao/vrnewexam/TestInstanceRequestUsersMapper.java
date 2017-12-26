package com.zxtech.esp.dao.vrnewexam;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewexam.TestInstanceRequestUsers;

public interface TestInstanceRequestUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestInstanceRequestUsers record);

    int insertSelective(TestInstanceRequestUsers record);

    Map<String, Object> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestInstanceRequestUsers record);

    int updateByPrimaryKey(TestInstanceRequestUsers record);
    
    List<Map<String, Object>> selectTestInstanceRequestUsersList(TestInstanceRequestUsers record);
    
    int deleteTestInstanceRequestUsers(TestInstanceRequestUsers record);
    
}