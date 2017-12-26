package com.zxtech.ui.dao.newexam;

import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewexamMark;
import com.zxtech.ui.vo.newexam.TNewexamMarkKey;

public interface TNewexamMarkMapper {
    int deleteByPrimaryKey(TNewexamMarkKey key);

    int insert(TNewexamMark record);

    int insertSelective(TNewexamMark record);

    Map<String, Object> selectByPrimaryKey(TNewexamMarkKey key);

    int updateByPrimaryKeySelective(TNewexamMark record);

    int updateByPrimaryKeyWithBLOBs(TNewexamMark record);

    int updateByPrimaryKey(TNewexamMark record);
}