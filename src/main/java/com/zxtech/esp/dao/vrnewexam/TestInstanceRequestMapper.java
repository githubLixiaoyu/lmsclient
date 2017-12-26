package com.zxtech.esp.dao.vrnewexam;

import java.util.List;
import java.util.Map;

import com.zxtech.esp.vo.vrnewexam.TestInstanceRequest;
import com.zxtech.ui.vo.newexam.TNewExamInfo;

public interface TestInstanceRequestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestInstanceRequest record);

    int insertSelective(TestInstanceRequest record);

    Map<String, Object> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestInstanceRequest record);

    int updateByPrimaryKey(TestInstanceRequest record);
    
    List<Map<String, Object>> selectTestInstanceRequestList(TestInstanceRequest record);
    
    int deleteTestInstanceRequest(List<String> ids);
    
    int sendTestInstanceRequest(List<String> ids);
    
    List<Map<String, Object>> selectTemplMilestoneStatusTypeList();
    
    List<Map<String, Object>> selectVrExamInfoByFilter (TNewExamInfo record);
    
    Map<String, Object> getVrNoExamNum(TNewExamInfo record);

    Map<String, Object> getVrExamedNum(TNewExamInfo record);
    
    Map<String, Object> getVrUserExamInfo(TNewExamInfo record);
    
    List<Map<String, Object>> selectVrAnswersByFilter(TNewExamInfo record);
}