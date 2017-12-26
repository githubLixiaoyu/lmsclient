package com.zxtech.ui.dao.newexam;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewexamDetails;
import com.zxtech.ui.vo.newexam.TNewexamDetailsKey;

public interface TNewexamDetailsMapper {
    int deleteByPrimaryKey(TNewexamDetailsKey key);

    int insert(TNewexamDetails record);

    int insertSelective(TNewexamDetails record);

    List<Map<String, Object>> selectExamScoreByFilter(TNewexamDetails record);

    Map<String, Object> selectByPrimaryKey(TNewexamDetailsKey key);

    int updateByPrimaryKeySelective(TNewexamDetails record);

    int updateByPrimaryKeyWithBLOBs(TNewexamDetails record);

    int updateByPrimaryKey(TNewexamDetails record);
}