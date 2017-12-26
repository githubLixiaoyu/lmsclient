package com.zxtech.ui.application.mobile.impl;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zxtech.support.util.IOUtil;
import com.zxtech.support.util.StatisticsScoreUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.application.mobile.MobileApplication;
import com.zxtech.ui.dao.app.TCourseCommentMapper;
import com.zxtech.ui.dao.app.TCourseInfoMapper;
import com.zxtech.ui.dao.app.TCoursePraiseMapper;
import com.zxtech.ui.dao.app.TCourseTypeMapper;
import com.zxtech.ui.dao.app.TRankingMapper;
import com.zxtech.ui.dao.app.TUserLearningCourseMapper;
import com.zxtech.ui.dao.bbs.TBbsPostImageMapper;
import com.zxtech.ui.dao.bbs.TBbsPostMapper;
import com.zxtech.ui.dao.bbs.TBbsPostReplyMapper;
import com.zxtech.ui.dao.bbs.TBbsTypeMapper;
import com.zxtech.ui.dao.newexam.TNewExamInfoMapper;
import com.zxtech.ui.dao.newexam.TNewexamDetailsMapper;
import com.zxtech.ui.dao.newexam.TNewexamMarkMapper;
import com.zxtech.ui.dao.newexam.TNewexamProcedureMapper;
import com.zxtech.ui.dao.permissions.TPermissionsStudentinfoMapper;
import com.zxtech.ui.vo.app.TCourseComment;
import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TCoursePraiseExample;
import com.zxtech.ui.vo.app.TCourseType;
import com.zxtech.ui.vo.app.TRanking;
import com.zxtech.ui.vo.app.TUserLearningCourse;
import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostImage;
import com.zxtech.ui.vo.bbs.TBbsPostImageExample;
import com.zxtech.ui.vo.bbs.TBbsPostReply;
import com.zxtech.ui.vo.bbs.TBbsTypeExample;
import com.zxtech.ui.vo.mobile.MBaseVO;
import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newexam.TNewexamDetails;
import com.zxtech.ui.vo.newexam.TNewexamDetailsKey;
import com.zxtech.ui.vo.newexam.TNewexamMark;
import com.zxtech.ui.vo.newexam.TNewexamProcedure;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@Named
public class MobileApplicationImpl implements MobileApplication{
	@Inject
	private TCourseTypeMapper tCourseTypeMapper;
	@Inject
	private TCourseInfoMapper tCourseInfoMapper;
	@Inject
	private TCourseCommentMapper tCourseCommentMapper;
	@Inject
	private TUserLearningCourseMapper tUserLearningCourseMapper;
	@Inject
	private TRankingMapper tRankingMapper;
	@Inject
	private TCoursePraiseMapper tCoursePariseMapper;
	@Inject
	private TPermissionsStudentinfoMapper tPermissionsStudentinfoMapper;
	@Inject
	private TBbsPostMapper tBbsPostMapper;
	@Inject
	private TBbsTypeMapper tBbsTypeMapper;
	@Inject
	private TBbsPostReplyMapper tBbsPostReplyMapper;
	@Inject
	private TBbsPostImageMapper tBbsPostImageMapper;
	@Inject
	private TNewExamInfoMapper tNewExamInfoMapper;
	@Inject
	private TNewexamMarkMapper tNewexamMarkMapper;
	@Inject
	private TNewexamProcedureMapper tNewexamProcedureMapper;
	@Inject
	private TNewexamDetailsMapper tNewexamDetailsMapper;
	
	@Override
	public List<Map<String, Object>> getCourseType(TCourseType record) {
		
		String file_path = Util.readPropertiesValue("CourseTypeImages");
		List<Map<String, Object>> selectCourseTypeParent = tCourseTypeMapper.selectCourseTypeParent();
		List<Map<String, Object>> selectCourseType = tCourseTypeMapper.selectCourseType(record);
		for(Map<String, Object> map:selectCourseTypeParent){
			List<Map<String, Object>> tempMap = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> subMap:selectCourseType){
				if(String.valueOf(map.get("parent_id")).equals(String.valueOf(subMap.get("parent_id")))){
					if(subMap.containsKey("image")){
						subMap.put("image_url", file_path+subMap.get("image"));
					}
					tempMap.add(subMap);
				}
			}
			map.put("childs", tempMap);
		}
		return selectCourseTypeParent;
	}
	
	@Override
	public MBaseVO getLearningCourseType(TCourseType record) {
		
		MBaseVO bvo = new MBaseVO();
		String file_path = Util.readPropertiesValue("CourseTypeImages");
		List<Map<String, Object>> selectCourseTypeParent = tCourseTypeMapper.selectCourseTypeParent();
		List<Map<String, Object>> selectLearningCourseType = tCourseTypeMapper.selectLearningCourseType(record);
		List<Map<String, Object>> tempParentMap = new ArrayList<>();
		for(Map<String, Object> map:selectCourseTypeParent){
			List<Map<String, Object>> tempMap = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> subMap:selectLearningCourseType){
				if(String.valueOf(map.get("parent_id")).equals(String.valueOf(subMap.get("parentid")))){
					if(subMap.containsKey("image")){
						subMap.put("image_url", file_path+subMap.get("image"));
					}
					tempMap.add(subMap);
				}
			}
			if(tempMap.size() > 0){
				map.put("childs", tempMap);
				tempParentMap.add(map);
			}
		}
		bvo.setData(tempParentMap);
		bvo.setStatus(MBaseVO.STATE_SUCC);
		return bvo;
	}

	@Override
	public List<Map<String, Object>> getCourseInfoByTypeId(String typeId, String userId) {
		
		List<Map<String, Object>> results = null;
		if (Util.isEmpty(userId)) {
			results = tCourseInfoMapper.selectCourseByTypeId(typeId);
		}else{
			results = tCourseInfoMapper.selectLearningCourse(typeId, userId);
		}
		
		
		String image_path = Util.readPropertiesValue("ScormImages");
		String file_path = Util.readPropertiesValue("ScormZip");
		if (results != null && results.size()>0) {
			
			
			for(Map<String, Object> map:results){
				if(map.containsKey("file_name")){
					map.put("file_url", file_path + map.get("file_name"));
				}
				if(map.containsKey("image_name")){
					map.put("image_url", image_path + map.get("image_name"));
				}
				if(map.containsKey("big_image_name")){
					map.put("big_image_url", image_path + map.get("big_image_name"));
				}
				if (map.get("course_id") != null ) {
					Map<String, Object> commentNumber = tCourseCommentMapper.getCommentNumByCourse(map.get("course_id").toString());
					map.put("comment_num", commentNumber.get("count"));
					
					Map<String, Object> praiseNum= tCoursePariseMapper.getPraiseNumByCourse(map.get("course_id").toString());
					map.put("praise_num", praiseNum.get("count"));
				}
				
			}
		}
		return results;
	}



	@Override
	public MBaseVO getCourseComment(String courseId) {
		MBaseVO bvo = new MBaseVO();
		if (Util.isEmpty(courseId)) {
			return bvo;
		}else{
			bvo.setStatus(MBaseVO.STATE_SUCC);
			String path = Util.readPropertiesValue("UserPhoto");
			List<Map<String, Object>> parentComments = tCourseCommentMapper.getCommentByCourseParent(courseId);
			List<Map<String, Object>> childComments = tCourseCommentMapper.getCommentByCourseChild(courseId);
			for (int i=0; i< parentComments.size(); i++) {
				Map<String, Object> parent = parentComments.get(i);
				if (parent.get("photo_name") != null) {
					parent.put("photo_url", path+parent.get("photo_name"));
				}
				//List<Map<String, Object>> childResults = new ArrayList<>();
				for (Map<String, Object> child:childComments) {
					if (parent.get("id").equals(child.get("parent_id"))) {
						if (child.get("photo_name") != null) {
							child.put("photo_url", path+child.get("photo_name"));
						}
						if (child.get("create_date") != null) {
							String[] convertReplyDate = Util.convertReplyDate(child.get("create_date").toString());
							child.put("create_date", convertReplyDate[0]);
							child.put("create_date_en", convertReplyDate[1]);
						}
						parentComments.add(i+1, child);
					}
				}
			}
			
			bvo.setData(parentComments);
			return bvo;
		}
		
	}


	@Override
	public MBaseVO replyComment(TCourseComment comment) {
		MBaseVO bvo = new MBaseVO();
		
		if (Util.isEmpty(comment.getOwnerUserId())  || Util.isEmpty(comment.getCourseId())) {
			return bvo;
		}else{
			comment.setId(Util.getUUID());
			comment.setCreateDate(new Date());
			int ret = tCourseCommentMapper.insertSelective(comment);
			if (ret == 0) {
				throw new RuntimeException();
			}else{
				bvo.setStatus(MBaseVO.STATE_SUCC);
			}
		}
		
		return bvo;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int setUserLearnCourseRecord(TUserLearningCourse vo) {
		
		try {
			Map<String, Object> map = StatisticsScoreUtil.getCourseScoreInfo(
				vo.getCourseid(),
				vo.getUserid(),
				vo.getCredits()
			);
			
			if (!"".equals(String.valueOf(map.get("learning")))) {
				Double credits = Double.valueOf(String.valueOf(map.get("learning")));
				BigDecimal b = new BigDecimal(credits); 
				credits = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				if ("insert".equals(String.valueOf(map.get("learningFlag")))) {
					vo.setLearningnumber(1);
					vo.setLearningstate("1");
					vo.setCredits(credits);
					tUserLearningCourseMapper.insertSelective(vo);
				} else if ("update".equals(String.valueOf(map.get("learningFlag")))) {
					vo.setCredits(credits);
					tUserLearningCourseMapper.updateByPrimaryKeySelective(vo);
				}
			}
			
			if (!"".equals(String.valueOf(map.get("ranking")))) {
				
				Double totalScore = Double.valueOf(String.valueOf(map.get("ranking")));
				BigDecimal b = new BigDecimal(totalScore); 
				totalScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				TRanking tRanking = new TRanking();
				tRanking.setId(vo.getUserid());
				tRanking.setTotalScore(totalScore);
				if ("insert".equals(String.valueOf(map.get("rankingFlag")))) {
					tRankingMapper.insertSelective(tRanking);
				} else if ("update".equals(String.valueOf(map.get("rankingFlag")))) {
					tRankingMapper.updateByPrimaryKeySelective(tRanking);
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public MBaseVO getRankingAll() {
		
		MBaseVO bvo = new MBaseVO();
		List<Map<String, Object>> results = tRankingMapper.selectRankingAll();
		if(results!= null && results.size()>0){
			
			String file_path = Util.readPropertiesValue("UserPhoto");
			for(Map<String, Object> map:results){
				
				if(map.containsKey("photo_name")){
					String photo_url = file_path + map.get("photo_name");
					map.put("photo_url", photo_url);
				}
			}
			bvo.setData(results);
			bvo.setStatus(MBaseVO.STATE_SUCC);
		}
		return bvo;
	}

	@Override
	public MBaseVO praiseCourse(TCoursePraise praise) {
		MBaseVO bvo = new MBaseVO();
		int ret = 0;
		
		if (Util.isEmpty(praise.getCourseId())  || Util.isEmpty(praise.getUserId())) {
			return bvo;
		}else{
			TCoursePraiseExample example = new TCoursePraiseExample();
			example.createCriteria().andCourseIdEqualTo(praise.getCourseId()).andUserIdEqualTo(praise.getUserId());
			List<TCoursePraise> selectByExample = tCoursePariseMapper.selectByExample(example);
			
			if (selectByExample != null && selectByExample.size()>0 ) {
				TCoursePraise tCoursePraise = selectByExample.get(0);
				ret = tCoursePariseMapper.deleteByPrimaryKey(tCoursePraise.getId());
			}else{
					praise.setId(Util.getUUID());
					praise.setCreateDate(new Date());
					ret = tCoursePariseMapper.insert(praise);
			}

			if (ret == 0) {
				throw new RuntimeException();
			}else{
				bvo.setStatus(MBaseVO.STATE_SUCC);
			}
		}
		
		return bvo;
	}

	@Override
	public MBaseVO uploadHeaderPhoto(String userId, Map<String, MultipartFile> fileMap) {
		MBaseVO bvo = new MBaseVO();
		
		if (Util.isEmpty(userId)) {
			return bvo;
		}else{
			for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
				MultipartFile file = entry.getValue();
				try {
					String realUpload =Util.readPropertiesValue("UserPhotoPath");
					String fileName = "headerphoto_"+System.currentTimeMillis() + ".png";
					String path = realUpload + "/" + fileName;
					IOUtil.writeFile(new File(path), file.getInputStream());
					TPermissionsStudentinfo record = new TPermissionsStudentinfo();
					record.setStudentid(userId);
					record.setPhotoname(fileName);
					int ret = tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(record);
					if (ret != 0) {
						bvo.setStatus(MBaseVO.STATE_SUCC);
						bvo.setMsg("上传成功");
						HashMap<String, String> dataMap = new HashMap<>();
						dataMap.put("file_name", fileName);
						dataMap.put("file_url", Util.readPropertiesValue("UserPhoto")+fileName);
						bvo.setData(dataMap);
					}
				} catch (IOException e) {
					throw new RuntimeException();
				}
	
			}
		}
		return bvo;
	}


	@Override
	public MBaseVO getUserInfo(String userId) throws Exception {
		MBaseVO bvo = new MBaseVO();
		if (Util.isEmpty(userId)) {
			return bvo;
		}else{
			HashMap<String, String> result = new HashMap<>();
			Map<String, Object> selectByPrimaryKey = tPermissionsStudentinfoMapper.selectByPrimaryKey(userId);
			if (selectByPrimaryKey != null  ) {
				if (selectByPrimaryKey.get("photoname") != null) {
					String user_photo_path = Util.readPropertiesValue("UserPhoto");
					result.put("photo_name", selectByPrimaryKey.get("photoname").toString());
					result.put("photo_url", user_photo_path+selectByPrimaryKey.get("photoname").toString());
				}
				
				result.put("name", selectByPrimaryKey.get("nickname") == null?"":selectByPrimaryKey.get("nickname").toString());
			}
			
			 List<Map<String, Object>> all = tRankingMapper.selectRankingAll();
			  if (all != null ) {
				for (int i=0; i<all.size(); i++) {
					String id = (String) all.get(i).get("id");
					if (id.equals(userId)) {
						result.put("rank", String.valueOf(i+1));
						break;
					}
				}
				
			}
			  //获取应考试数
			TNewExamInfo tNewExamInfo = new TNewExamInfo();
			tNewExamInfo.setLoginUserId(userId);
			tNewExamInfo.setReviewStatus("0");
			List<Map<String, Object>> list = tNewExamInfoMapper.selectExamInfoByFilter(tNewExamInfo);
			int examCount = 0;
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			if (list != null && list.size() > 0) {
				for (Map<String, Object> m : list) {
					String startTime = String.valueOf(m.get("starttime"));
					String endTime = String.valueOf(m.get("endtime"));
					if (!Util.isEmpty(startTime) && !Util.isEmpty(endTime)) {
						Date dts = df.parse(startTime);
						Date dte = df.parse(endTime);
						Date dtn = df.parse(df.format(new Date()));
						if (dtn.getTime() >= dts.getTime() && dtn.getTime() <= dte.getTime()) {
							examCount++;
						}
					}else if (Util.isEmpty(startTime) && Util.isEmpty(endTime)) {
						examCount++;
					}

				}
			}
			result.put("exam", String.valueOf(examCount));
			bvo.setStatus(MBaseVO.STATE_SUCC);
			bvo.setData(result);
		}
		return bvo;
	}

	@Override
	public MBaseVO updateUserInfo(TPermissionsStudentinfo info) {
		MBaseVO bvo = new MBaseVO();
		if (info == null) {
			return bvo;
		}else{
			int updateByPrimaryKeySelective = tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(info);
			
			if (updateByPrimaryKeySelective != 0) {
				bvo.setStatus(MBaseVO.STATE_SUCC);
				bvo.setMsg("上传成功");
			}
		}
		return bvo;
	}

	@Override
	public MBaseVO releasePost(TBbsPost bbs, Map<String, MultipartFile> fileMap) {
		MBaseVO bvo = new MBaseVO();
		if (bbs == null) {
			return bvo;
		}else{
			String postId = Util.getUUID();
			bbs.setId(postId);
			bbs.setEnableFlag("1");
			bbs.setLookNum(0);
			bbs.setCreateDate(new Date());
			
			int insert = tBbsPostMapper.insert(bbs);
			if (insert != 0) {
				bvo.setStatus(MBaseVO.STATE_SUCC);
				bvo.setMsg("发布成功");
			}
			try {
				for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
					MultipartFile file = entry.getValue();
					
						String realUpload =Util.readPropertiesValue("UserPhotoPath");
						String fileName = "post_"+System.currentTimeMillis() + ".png";
						String uploadPath =Util.readPropertiesValue("UserPhoto");
						String path = realUpload  +"/"+ fileName;
						IOUtil.writeFile(new File(path), file.getInputStream());
						TBbsPostImage postImage = new TBbsPostImage();
						postImage.setId(Util.getUUID());
						postImage.setPhotoName(fileName);
						postImage.setPhotoUrl(uploadPath+fileName);
						postImage.setEnableFlag("1");
						postImage.setPostId(postId);
						int ret = tBbsPostImageMapper.insertSelective(postImage);
						if (ret != 0) {
							bvo.setStatus(MBaseVO.STATE_SUCC);
							bvo.setMsg("发布成功");
						}
				}
				
				
			} catch (IOException e) {
				throw new RuntimeException();
			}

		}
		return bvo;
	}

	@Override
	public List<Map<String, Object>> getBbsType() {
		return tBbsTypeMapper.selectByExample(new TBbsTypeExample());
	}

	@Override
	public List<Map<String, Object>> getBbsPost() {
		List<Map<String, Object>> bbsPosts = tBbsPostMapper.selectBbsPost();
		String realUpload =Util.readPropertiesValue("UserPhoto");
		for (Map<String, Object> postMap:bbsPosts) {
			TBbsPostImageExample example = new TBbsPostImageExample();
			example.createCriteria().andEnableFlagEqualTo("1").andPostIdEqualTo(postMap.get("id").toString());
			List<Map<String, Object>> selectByExample = tBbsPostImageMapper.selectByExample(example);
			if (selectByExample != null && selectByExample.size()>0) {
				postMap.put("images", selectByExample);
			}
			
			if (postMap.get("post_user_photo") != null) {
				postMap.put("post_user_url", realUpload+postMap.get("post_user_photo"));
			}
		}
		return bbsPosts;
	}

	@Override
	public MBaseVO replyPost(TBbsPostReply reply) {
		MBaseVO bvo = new MBaseVO();
		
		if (Util.isEmpty(reply.getOwnerUserId())  || Util.isEmpty(reply.getPostId())) {
			return bvo;
		}else{
			reply.setId(Util.getUUID());
			reply.setCreateDate(new Date());
			int ret = tBbsPostReplyMapper.insertSelective(reply);
			if (ret == 0) {
				throw new RuntimeException();
			}else{
				bvo.setStatus(MBaseVO.STATE_SUCC);
			}
			
		}
		
		return bvo;
	}

	@Override
	public MBaseVO getPostComment(String post_id) {
		MBaseVO bvo = new MBaseVO();
		if (Util.isEmpty(post_id)) {
			return bvo;
		}else{
			bvo.setStatus(MBaseVO.STATE_SUCC);
			
			String path = Util.readPropertiesValue("UserPhoto");
			List<Map<String, Object>> parentComments = tBbsPostReplyMapper.getCommentByPostParent(post_id);
			List<Map<String, Object>> childComments = tBbsPostReplyMapper.getCommentByPostChild(post_id);
			for (int i=0; i< parentComments.size(); i++) {
				Map<String, Object> parent = parentComments.get(i);
				if (parent.get("photo_name") != null) {
					parent.put("photo_url", path+parent.get("photo_name"));
				}
				//List<Map<String, Object>> childResults = new ArrayList<>();
				for (Map<String, Object> child:childComments) {
					if (parent.get("id").equals(child.get("parent_id"))) {
						if (child.get("photo_name") != null) {
							child.put("photo_url", path+child.get("photo_name"));
						}
						if (child.get("create_date") != null) {
							String[] convertReplyDate = Util.convertReplyDate(child.get("create_date").toString());
							child.put("create_date", convertReplyDate[0]);
							child.put("create_date_en", convertReplyDate[1]);
						}
						parentComments.add(i+1, child);
						
					}
				
					
				}
			}
			
			bvo.setData(parentComments);
			return bvo;
		}
	}

	@Override
	public MBaseVO lookPost(String post_id) {
		MBaseVO bvo = new MBaseVO();
		
		if (Util.isEmpty(post_id) ) {
			return bvo;
		}else{
			TBbsPost selectPost = tBbsPostMapper.selectByPrimaryKey(post_id);
			if (selectPost != null) {
				TBbsPost record = new TBbsPost();
				record.setId(post_id);
				record.setLookNum(selectPost.getLookNum()+1);
				int ret = tBbsPostMapper.updateByPrimaryKeySelective(record);
				if (ret == 0) {
					throw new RuntimeException();
				}else{
					bvo.setStatus(MBaseVO.STATE_SUCC);
				}
			}
		}
		
		return bvo;
	}

	@Override
	public MBaseVO saveNewexamMark(TNewexamMark tNewexamMark) {

		MBaseVO bvo = new MBaseVO();
		Map<String, Object> selectByPrimaryKey = tNewexamMarkMapper.selectByPrimaryKey(tNewexamMark);
		int record = 0;
		if(selectByPrimaryKey!=null && selectByPrimaryKey.size()>0){
			record = tNewexamMarkMapper.updateByPrimaryKeySelective(tNewexamMark);
		}else{
			record = tNewexamMarkMapper.insertSelective(tNewexamMark);
		}
		if(record>0){
			bvo.setStatus(MBaseVO.STATE_SUCC);
		}else{
			throw new RuntimeException();
		}
		return bvo;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public MBaseVO handExamPaper(String userId, String examId, String paperId, int continue_time, ArrayList<HashMap<String,String>> list) {
		
		MBaseVO vo = new MBaseVO();
		int total = 0;
		int errorNum = 0;
		int rightNum = 0;
		for(HashMap<String,String> map:list){
			
			String typeId = map.get("typeId");
			String answer = map.get("answer");
			String questionId = map.get("questionId");
			String userAnswer = map.get("userAnswer");
			String problemState = map.get("problemState");
			int questionScore = Integer.parseInt(map.get("questionScore"));
			
			TNewexamProcedure tnp = new TNewexamProcedure();
			tnp.setExamid(examId);
			tnp.setPaperid(paperId);
			tnp.setUserid(userId);
			tnp.setQuestionid(questionId);
			tnp.setProblemstate(problemState);
			tnp.setAnswer(userAnswer);
			tnp.setTypeid(typeId);
			saveExamQuestionAnswer(tnp);
			
			if(null != userAnswer && !"".equals(userAnswer)){
				
				String[] answers = answer.split(",");
				String[] userAnswers = userAnswer.split(",");
				List<String> answerList = Arrays.asList(answers);
				
				if(userAnswers.length != answerList.size()){
					errorNum++;
					continue;
				}else{
					boolean isRight = false;
					for(String str:userAnswers){
						if(!answerList.contains(str)){
							isRight = false;
							break;
						}
						isRight = true;
					}
					if(isRight){
						rightNum++;
						total += questionScore; 
					}else{
						errorNum++;
					}
				}
			}else{
				errorNum++;
			}
		}
		
		TNewexamMark tNewexamMark = new TNewexamMark();
		tNewexamMark.setExamid(examId);
		tNewexamMark.setPaperid(paperId);
		tNewexamMark.setUserid(userId);
		tNewexamMark.setScore(new BigDecimal(total));
		saveNewexamMark(tNewexamMark);
		
		TNewexamDetails tNewexamDetails = new TNewexamDetails();
		tNewexamDetails.setExamid(examId);
		tNewexamDetails.setPaperid(paperId);
		tNewexamDetails.setUserid(userId);
		tNewexamDetails.setReviewstatus("1");
		tNewexamDetails.setEndtime(new Date());
		tNewexamDetails.setContinuetime((double)continue_time);
		tNewexamDetailsMapper.updateByPrimaryKeySelective(tNewexamDetails);
		
		Map<String,Object> results = new HashMap<>();
		results.put("errorNum",errorNum);
		results.put("rightNum",rightNum);
		results.put("total",total);
		vo.setData(results);
		return vo;
	}
	
	public int saveExamQuestionAnswer(TNewexamProcedure tNewexamProcedure){
		Map<String, Object> map = tNewexamProcedureMapper.selectByPrimaryKey(tNewexamProcedure);
		int returnFlag = 0;
		if (map == null) {
			returnFlag = tNewexamProcedureMapper.insertSelective(tNewexamProcedure);
		} else {
			returnFlag = tNewexamProcedureMapper.updateByPrimaryKeySelective(tNewexamProcedure);
		}
		return returnFlag;
	}

	@Override
	public MBaseVO startExam(TNewexamDetails tNewexamDetails) {
		MBaseVO vo = new MBaseVO();
		if (tNewexamDetails == null || Util.isEmpty(tNewexamDetails.getExamid())
				|| Util.isEmpty(tNewexamDetails.getPaperid()) || Util.isEmpty(tNewexamDetails.getUserid())) {
			return vo;
		} else {
			TNewexamDetailsKey key = new TNewexamDetailsKey();
			key.setExamid(tNewexamDetails.getExamid());
			key.setPaperid(tNewexamDetails.getPaperid());
			key.setUserid(tNewexamDetails.getUserid());
			Map<String, Object> selectByPrimaryKey = tNewexamDetailsMapper.selectByPrimaryKey(key);
			if (selectByPrimaryKey == null) {
				tNewexamDetails.setStarttime(new Date());
				tNewexamDetails.setReviewstatus("0");
				tNewexamDetails.setIfappend("0");
				tNewexamDetails.setIfapplay("0");
				int insert = tNewexamDetailsMapper.insert(tNewexamDetails);
				if (insert != 0) {
					vo.setStatus(MBaseVO.STATE_SUCC);
				}
			}else{
				vo.setStatus(MBaseVO.STATE_SUCC);
			}

		}
		
		return vo;
	}
}