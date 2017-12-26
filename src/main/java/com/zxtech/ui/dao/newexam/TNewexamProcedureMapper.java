package com.zxtech.ui.dao.newexam;

import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewexamProcedure;
import com.zxtech.ui.vo.newexam.TNewexamProcedureKey;

public interface TNewexamProcedureMapper {
    int deleteByPrimaryKey(TNewexamProcedureKey key);

    int insert(TNewexamProcedure record);

    int insertSelective(TNewexamProcedure record);

    Map<String, Object> selectByPrimaryKey(TNewexamProcedureKey key);

    int updateByPrimaryKeySelective(TNewexamProcedure record);

    int updateByPrimaryKeyWithBLOBs(TNewexamProcedure record);

    int updateByPrimaryKey(TNewexamProcedure record);
}