package com.zxtech.ui.dao.permissions;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.permissions.TLoginInfo;

public interface TLoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TLoginInfo record);

    int insertSelective(TLoginInfo record);

    TLoginInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TLoginInfo record);

    int updateByPrimaryKey(TLoginInfo record);
    
    List<Map<String, Object>> selectLoginIfoList(TLoginInfo record);
}