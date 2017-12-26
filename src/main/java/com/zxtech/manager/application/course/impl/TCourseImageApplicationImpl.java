package com.zxtech.manager.application.course.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.manager.application.course.TCourseImageApplication;
import com.zxtech.ui.dao.course.TCourseImageMapper;
import com.zxtech.ui.vo.course.TCourseImage;

@Named
public class TCourseImageApplicationImpl implements TCourseImageApplication {
	
	@Inject
	private TCourseImageMapper tCourseImageMapper;

	@Override
	public Map<String, Object> selectTCourseImageByCourseid(String courseid) throws Exception{
		TCourseImage  record = new TCourseImage();
		record.setCourseid(courseid);
		List<Map<String, Object>> list = tCourseImageMapper.selectCourseImageList(record);
		return list.size()>0 ? list.get(0) : null;
	}

}
