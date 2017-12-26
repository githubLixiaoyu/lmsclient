package com.zxtech.esp.dao.user;

import java.util.Map;

import com.zxtech.esp.vo.user.MhUser;

public interface MhUserMapper {
    int deleteByPrimaryKey(String cnUserId);

    int insert(MhUser record);

    int insertSelective(MhUser record);

    Map<String, Object> selectByPrimaryKey(String cnUserId);

    int updateByPrimaryKeySelective(MhUser record);

    int updateByPrimaryKey(MhUser record);
}