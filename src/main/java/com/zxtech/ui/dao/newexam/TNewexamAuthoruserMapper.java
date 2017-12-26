package com.zxtech.ui.dao.newexam;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewexamAuthoruser;

public interface TNewexamAuthoruserMapper {
    int deleteByPrimaryKey(TNewexamAuthoruser key);

    int insert(TNewexamAuthoruser record);

    int insertSelective(TNewexamAuthoruser record);

    TNewexamAuthoruser selectByPrimaryKey(TNewexamAuthoruser key);

    int updateByPrimaryKeySelective(TNewexamAuthoruser record);

    int updateByPrimaryKey(TNewexamAuthoruser record);
    
    List<Map<String, Object>> selectNewexamAuthoruserList(TNewexamAuthoruser record);
    
    List<Map<String, Object>> selectCanSelectAuthoruserList(TNewexamAuthoruser record);
    
    int deleteNewexamAuthoruser(List<String> ids);
    //查询考试结果
    List<Map<String, Object>> selectExamResultList(TNewexamAuthoruser record);
    
    List<Map<String, Object>> selectUserForTestInstanceRequestList(TNewexamAuthoruser record);
    
    List<Map<String, Object>> selectCanSelectForTestInstanceRequestList(TNewexamAuthoruser record);
}