package com.zxtech.manager.application.permissions.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.permissions.TPermissionsStudentDepartCourseApplication;
import com.zxtech.ui.dao.permissions.TPermissionsStudentDepartCourseMapper;
import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;

@Named
public class TPermissionsStudentDepartCourseApplicationImpl implements TPermissionsStudentDepartCourseApplication {
	@Inject
	private TPermissionsStudentDepartCourseMapper tPermissionsStudentDepartCourseMapper;

	@Override
	public PageInfo<Map<String, Object>> getCourseByDepartId(TPermissionsStudentDepart record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tPermissionsStudentDepartCourseMapper.getCourseByDepartId(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}
}
