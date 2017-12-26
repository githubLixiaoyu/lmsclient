package com.zxtech.support.util;

import java.util.HashMap;
import java.util.Map;

import com.zxtech.support.spring.ApplicationContextHelper;
import com.zxtech.ui.dao.app.TRankingMapper;
import com.zxtech.ui.dao.app.TUserLearningCourseMapper;
import com.zxtech.ui.vo.app.TUserLearningCourse;

public class StatisticsScoreUtil {
	
	/**
	 * 统计课程分数
	 * @param dest copy后的对象
	 * @param source copy数据源
	 * @throws Exception
	 */
	public static Map<String, Object> getCourseScoreInfo(String courseId, String userId, Double credits) throws Exception {
		
		TUserLearningCourseMapper tUserLearningCourseMapper = ApplicationContextHelper.getBean(TUserLearningCourseMapper.class);
		TRankingMapper tRankingMapper = ApplicationContextHelper.getBean(TRankingMapper.class);
		
		Map<String, Object> map = new HashMap<String, Object>();
		TUserLearningCourse tUserLearningCourse = new TUserLearningCourse();
		tUserLearningCourse.setCourseid(courseId);
		tUserLearningCourse.setUserid(userId);
		tUserLearningCourse.setLearningnumber(1);
		TUserLearningCourse bean = tUserLearningCourseMapper.selectByPrimaryKey(tUserLearningCourse);
		Map<String, Object> rankingMap =  tRankingMapper.selectByPrimaryKey(userId);
		
		if (bean == null && rankingMap == null) {
			map.put("learningFlag", "insert");
			map.put("rankingFlag", "insert");
			map.put("learning", credits);
			map.put("ranking", credits);
		} else if (bean == null && rankingMap != null) {
			map.put("learningFlag", "insert");
			map.put("rankingFlag", "update");
			map.put("learning", credits);
			map.put("ranking", credits + Double.valueOf(String.valueOf(rankingMap.get("total_score"))));
		} else if (bean != null && rankingMap == null) {
			map.put("learningFlag", "update");
			map.put("rankingFlag", "insert");
			Double beanCredits = bean.getCredits();
			if (credits > beanCredits) {
				map.put("learning", credits);
			} else {
				map.put("learning", "");
			}
			map.put("ranking", credits);
		} else {
			map.put("learningFlag", "update");
			map.put("rankingFlag", "update");
			Double beanCredits = bean.getCredits();
			if (credits > beanCredits) {
				map.put("learning", credits);
				map.put("ranking", Double.valueOf(String.valueOf(rankingMap.get("total_score"))) + credits - beanCredits);
			} else {
				map.put("learning", "");
				map.put("ranking", Double.valueOf(String.valueOf(rankingMap.get("total_score"))));
			}
		}
		return map;
	}
}