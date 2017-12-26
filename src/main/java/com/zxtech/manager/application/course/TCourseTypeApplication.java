package com.zxtech.manager.application.course;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.app.TCourseType;

public interface TCourseTypeApplication {
	//查询
	public PageInfo<Map<String, Object>> selectCourseTypeList(TCourseType record, int pageNum, int pageSize) throws Exception;
	//查询
	public List<Map<String, Object>> selectCourseTypeListById(int parentid) throws Exception;
	//保存
	public int saveCourseType(TCourseType record) throws Exception;
	//删除
	public int deleteCourseType(String[] idList) throws Exception;
}
