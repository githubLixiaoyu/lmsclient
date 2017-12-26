package com.zxtech.ui.dao.newexam;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newexam.TNewExamInfoWithBLOBs;

public interface TNewExamInfoMapper {
    int deleteByPrimaryKey(String examid);

    int insert(TNewExamInfoWithBLOBs record);

    int insertSelective(TNewExamInfo record);

    Map<String, Object> selectByPrimaryKey(String examid);

    int updateByPrimaryKeySelective(TNewExamInfo record);

    int updateByPrimaryKeyWithBLOBs(TNewExamInfoWithBLOBs record);

    int updateByPrimaryKey(TNewExamInfo record);
    
    List<Map<String, Object>> selectNewExamInfoList(TNewExamInfo record);
    
    List<Map<String, Object>> selectExamInfoByFilter(TNewExamInfo record);

    Map<String, Object> getUserExamInfo(TNewExamInfo record);

    Map<String, Object> getNoExamNum(TNewExamInfo record);

    Map<String, Object> getExamedNum(TNewExamInfo record);
    
    int deleteNewExamInfo(List<String> ids);
    
    int sendNewExamInfo(List<String> ids);
    
    int getExamCount(TNewExamInfo record);
}