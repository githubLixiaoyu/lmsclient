package com.zxtech.ui.dao.app;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.app.TCourseInfo;
import com.zxtech.ui.vo.app.TCourseInfoWithBLOBs;

public interface TCourseInfoMapper {
    int deleteByPrimaryKey(String courseid);

    int insert(TCourseInfoWithBLOBs record);

    int insertSelective(TCourseInfoWithBLOBs record);

    Map<String,Object> selectByPrimaryKey(String courseid);

    int updateByPrimaryKeySelective(TCourseInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TCourseInfoWithBLOBs record);

    int updateByPrimaryKey(TCourseInfo record);
    
    List<Map<String,Object>> selectByCourseType(TCourseInfo record);
    
    List<Map<String,Object>> selectCourseInfoByFillter(TCourseInfo record);
    
    List<Map<String,Object>> selectCourseDetailByCourseId(TCourseInfo record);
    
    List<Map<String,Object>> selectCourseByTypeId(String typeId);
    
    List< Map<String,Object>> selectLearningCourse(String typeId, String userId);
    

    List<Map<String,Object>> selectCourseInfoList(TCourseInfoWithBLOBs record);
    
    //删除
  	public int deleteCourseInfo(List<String> ids);
  	//发布
  	public int sendCourseInfo(TCourseInfoWithBLOBs record);
}