package com.zxtech.ui.application.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zxtech.ui.vo.app.TCourseComment;
import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TCourseType;
import com.zxtech.ui.vo.app.TUserLearningCourse;
import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostReply;
import com.zxtech.ui.vo.mobile.MBaseVO;
import com.zxtech.ui.vo.newexam.TNewexamDetails;
import com.zxtech.ui.vo.newexam.TNewexamMark;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

public interface MobileApplication {
	
	public List<Map<String, Object>> getCourseType(TCourseType record);
	public List<Map<String, Object>> getCourseInfoByTypeId(String typeId, String userId);
	
	public MBaseVO getCourseComment(String courseId) ;
	
	public MBaseVO replyComment(TCourseComment comment);
	
	public int setUserLearnCourseRecord(TUserLearningCourse vo);
	
	public MBaseVO getRankingAll();
	
	public MBaseVO getLearningCourseType(TCourseType record);
	
	public MBaseVO praiseCourse(TCoursePraise praise);
	
	public MBaseVO uploadHeaderPhoto(String userId, Map<String, MultipartFile> fileMap);
	
	public MBaseVO getUserInfo(String userId) throws Exception;
	
	public MBaseVO updateUserInfo(TPermissionsStudentinfo info);
	
	public MBaseVO releasePost(TBbsPost bbs, Map<String, MultipartFile> fileMap);
	
	public List<Map<String, Object>> getBbsType();
	
	public List<Map<String, Object>> getBbsPost();
	
	public MBaseVO replyPost(TBbsPostReply reply);
	
	public MBaseVO getPostComment(String post_id) ;
	
	public MBaseVO saveNewexamMark(TNewexamMark tNewexamMark);
	
	public MBaseVO handExamPaper(String userId,String examId,String paperId,int continue_time,ArrayList<HashMap<String,String>> list);
	
	public MBaseVO lookPost(String post_id) ;
	
	public MBaseVO startExam(TNewexamDetails tNewexamDetails) ;
}
