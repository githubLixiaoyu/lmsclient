package com.zxtech.manager.application.course;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.app.TCourseInfoWithBLOBs;

public interface TCourseInfoApplication {
	//查询
	public PageInfo<Map<String, Object>> selectCourseInfoList(TCourseInfoWithBLOBs record, int pageNum, int pageSize) throws Exception;
	//保存
	public int saveCourseInfo(TCourseInfoWithBLOBs record) throws Exception;
	//删除
	public int deleteCourseInfo(String[] idList) throws Exception;
	//发布
	public int sendCourseInfo(String[] idList) throws Exception;
}
