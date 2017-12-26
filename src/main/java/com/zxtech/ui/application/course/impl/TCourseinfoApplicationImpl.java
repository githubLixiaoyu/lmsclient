package com.zxtech.ui.application.course.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zxtech.support.util.StatisticsScoreUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.application.course.TCourseinfoApplication;
import com.zxtech.ui.dao.app.TCourseInfoMapper;
import com.zxtech.ui.dao.app.TCoursePraiseMapper;
import com.zxtech.ui.dao.app.TRankingMapper;
import com.zxtech.ui.dao.app.TUserLearningCourseMapper;
import com.zxtech.ui.vo.app.TCourseInfo;
import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TRanking;
import com.zxtech.ui.vo.app.TUserLearningCourse;

@Named
public class TCourseinfoApplicationImpl implements TCourseinfoApplication{
	@Inject
	private TCourseInfoMapper tCourseInfoMapper;
	@Inject
	private TCoursePraiseMapper tCoursePraiseMapper;
	@Inject
	private TUserLearningCourseMapper tUserLearningCourseMapper;
	@Inject
	private TRankingMapper tRankingMapper;
	
	@Override
	public Map<String, Object> selectByCourseType(HttpServletRequest request, TCourseInfo tCourseInfo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = tCourseInfoMapper.selectByCourseType(tCourseInfo);
		if (list != null && list.size() > 0) {
			map.put("coursetypename", list.get(0).get("courseTypeName"));
			map.put("coursetypenameEn", list.get(0).get("coursetypenameEn"));
			map.put("list", list);
		} else {
			map.put("list", new ArrayList<Map<String, Object>>());
		}

		String showPath = "http://" + request.getServerName();
		if (!"80".equals(request.getServerPort())) {
			showPath += ":" + request.getServerPort();
		}
		showPath += "/lmsFiles/scormImages/";
		map.put("path", showPath);
		
		String espPath = Util.readPropertiesValue("EspPath");
		map.put("espPath", espPath);
		
		return map;
	}
	@Override
	public Map<String, Object> selectCourseDetailByCourseId(HttpServletRequest request, TCourseInfo tCourseInfo) throws Exception{
		Map<String, Object> map = null;
		List<Map<String, Object>> list = tCourseInfoMapper.selectCourseDetailByCourseId(tCourseInfo);
		if (list != null && list.size() > 0) {
			map = list.get(0);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("createtime", formatter.format(map.get("createtime")));
		} else {
			map = new HashMap<String, Object>();
		}

		String showPath = "http://" + request.getServerName();
		if (!"80".equals(request.getServerPort())) {
			showPath += ":" + request.getServerPort();
		}
		
		showPath += "/lmsFiles/scormImages/";
		
		map.put("scormImagePath", showPath);
		
		String espPath = Util.readPropertiesValue("EspPath");
		map.put("espPath", espPath);
		
		return map;
	}
	
	@Override
	public Map<String, Object> selectCourseInfoByFillter(TCourseInfo tCourseInfo) throws Exception{
		List<Map<String, Object>> list = tCourseInfoMapper.selectCourseInfoByFillter(tCourseInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		String espPath = Util.readPropertiesValue("EspPath");
		map.put("espPath", espPath);
		return map;
	}
	
	@Override
	public int addPraise(TCoursePraise tCoursePraise) throws Exception{
		tCoursePraise.setId(Util.getUUID());
		tCoursePraise.setCreateDate(new Date());
		return tCoursePraiseMapper.insert(tCoursePraise);
	}
	
	@Override
	public int removePraise(TCoursePraise tCoursePraise) throws Exception{
		return tCoursePraiseMapper.deleteByCourseIdAndUserId(tCoursePraise);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int setUserLearnCourseScore(TUserLearningCourse tUserLearningCourse) throws Exception{
		Map<String, Object> map = StatisticsScoreUtil.getCourseScoreInfo(
				tUserLearningCourse.getCourseid(),
				tUserLearningCourse.getUserid(),
				tUserLearningCourse.getCredits()
		);
		if (!"".equals(String.valueOf(map.get("learning")))) {
			Double credits = Double.valueOf(String.valueOf(map.get("learning")));
			BigDecimal b = new BigDecimal(credits); 
			credits = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
			if ("insert".equals(String.valueOf(map.get("learningFlag")))) {
				tUserLearningCourse.setLearningnumber(1);
				tUserLearningCourse.setLearningstate("1");
				tUserLearningCourse.setCredits(credits);
				tUserLearningCourseMapper.insertSelective(tUserLearningCourse);
			} else if ("update".equals(String.valueOf(map.get("learningFlag")))) {
				tUserLearningCourse.setCredits(credits);
				tUserLearningCourseMapper.updateByPrimaryKeySelective(tUserLearningCourse);
			}
		}
		if (!"".equals(String.valueOf(map.get("ranking")))) {
			Double totalScore = Double.valueOf(String.valueOf(map.get("ranking")));
			BigDecimal b = new BigDecimal(totalScore); 
			totalScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			TRanking tRanking = new TRanking();
			tRanking.setId(tUserLearningCourse.getUserid());
			tRanking.setTotalScore(totalScore);
			if ("insert".equals(String.valueOf(map.get("rankingFlag")))) {
				tRankingMapper.insertSelective(tRanking);
			} else if ("update".equals(String.valueOf(map.get("rankingFlag")))) {
				tRankingMapper.updateByPrimaryKeySelective(tRanking);
			}
		}
		return 1;
	}
	
	@Override
	public int addUserLearnCourse(TUserLearningCourse tUserLearningCourse) throws Exception{
		tUserLearningCourse.setLearningnumber(1);
		tUserLearningCourse.setLearningstate("1");
		tUserLearningCourse.setCredits(0d);
		return tUserLearningCourseMapper.insertSelective(tUserLearningCourse);
	}
}
