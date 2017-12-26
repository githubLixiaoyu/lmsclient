package com.zxtech.ui.controller.mobile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxtech.common.application.function.CommonApplication;
import com.zxtech.manager.application.permissions.TPermissionsStudentinfoApplication;
import com.zxtech.ui.application.exam.TNewexamDetailsApplication;
import com.zxtech.ui.application.exam.TNewexamInfoApplication;
import com.zxtech.ui.application.exam.TNewexamProcedureApplication;
import com.zxtech.ui.application.mobile.MobileApplication;
import com.zxtech.ui.vo.app.TCourseComment;
import com.zxtech.ui.vo.app.TCoursePraise;
import com.zxtech.ui.vo.app.TCourseType;
import com.zxtech.ui.vo.app.TUserLearningCourse;
import com.zxtech.ui.vo.bbs.TBbsPost;
import com.zxtech.ui.vo.bbs.TBbsPostReply;
import com.zxtech.ui.vo.mobile.MBaseVO;
import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newexam.TNewexamDetails;
import com.zxtech.ui.vo.newexam.TNewexamProcedure;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@RestController
@RequestMapping("/ui/mobile")
public class MobileController {

	private static Logger log = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private MobileApplication mobileApplication;
	@Autowired
	private TNewexamInfoApplication tNewexamInfoApplication;
	@Autowired
	private CommonApplication commonApplication;
	@Autowired
	private TNewexamProcedureApplication tNewexamProcedureApplication;
	@Autowired
	private TNewexamDetailsApplication tNewexamDetailsApplication;
	@Autowired
	private TPermissionsStudentinfoApplication tPermissionsStudentinfoApplication;

	/**
	 * 查询课程分类信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCourseCate")
	public MBaseVO findCourseCate(TCourseType record) throws Exception {

		List<Map<String, Object>> results = mobileApplication.getCourseType(record);
		MBaseVO bvo = new MBaseVO();
		if (results != null) {
			bvo.setStatus(MBaseVO.STATE_SUCC);
			bvo.setData(results);
		}
		return bvo;
	}

	/**
	 * 按课程分类查询课程信息
	 * 
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCourse")
	public MBaseVO findCourse(String typeId, String userId) throws Exception {

		List<Map<String, Object>> results = mobileApplication.getCourseInfoByTypeId(typeId, userId);
		MBaseVO bvo = new MBaseVO();
		if (results != null) {
			bvo.setStatus(MBaseVO.STATE_SUCC);
			bvo.setData(results);
		}
		return bvo;
	}

	/**
	 * 上传成绩
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setUserLearnCourseInfo")
	public MBaseVO setUserLearnCourseInfo(TUserLearningCourse vo) throws Exception {

		int record = mobileApplication.setUserLearnCourseRecord(vo);
		MBaseVO bvo = new MBaseVO();
		if (record > 0) {
			bvo.setStatus(MBaseVO.STATE_SUCC);
		} else {
			bvo.setMsg("提交成绩异常");
		}
		return bvo;
	}

	@RequestMapping("/findLearningCourseType")
	public MBaseVO findLearningCourseType(TCourseType record) throws Exception {
		return mobileApplication.getLearningCourseType(record);
	}

	/**
	 * 获得排名
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findRankingAll")
	public MBaseVO findRankingAll(TUserLearningCourse vo) throws Exception {
		return mobileApplication.getRankingAll();
	}

	/**
	 * 获取课程下所有评论及回复
	 * 
	 * @param course_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCourseComment.do")
	@ResponseBody
	public MBaseVO getCourseComment(String course_id) throws Exception {
		return mobileApplication.getCourseComment(course_id);
	}

	/**
	 * 课件评论及回复
	 * 
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/replyComment.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO replyComment(TCourseComment comment) throws Exception {
		MBaseVO replyComment = null;
		try {
			replyComment = mobileApplication.replyComment(comment);
		} catch (Exception e) {
			log.error(e.getMessage());
			replyComment.setStatus(MBaseVO.STATE_FAIL);
		}

		return replyComment;
	}

	/**
	 * 课程的点赞与取消
	 * 
	 * @param praise
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/praiseCourse.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO praiseCourse(TCoursePraise praise) throws Exception {
		MBaseVO replyComment = null;
		try {
			replyComment = mobileApplication.praiseCourse(praise);
		} catch (Exception e) {
			log.error(e.getMessage());
			replyComment.setStatus(MBaseVO.STATE_FAIL);
		}

		return replyComment;
	}

	/**
	 * 上传头像
	 * 
	 * @param userId
	 * @param fileMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadHeaderPhoto.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO uploadHeaderPhoto(String userId, HttpServletRequest request) throws Exception {
		MBaseVO replyComment = null;
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			replyComment = mobileApplication.uploadHeaderPhoto(userId, fileMap);
		} catch (Exception e) {
			log.error(e.getMessage());
			replyComment.setStatus(MBaseVO.STATE_FAIL);
		}

		return replyComment;
	}

	/**
	 * 获取个人信息 (头像，姓名，排名，消息，考试)
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserInfo.do")
	@ResponseBody
	public MBaseVO getUserInfo(String userId) throws Exception {
		MBaseVO info = new MBaseVO();
		try {
			info = mobileApplication.getUserInfo(userId);
		} catch (Exception e) {
			log.error(e.getMessage());
			info.setStatus(MBaseVO.STATE_FAIL);
		}

		return info;
	}
	
	/**
	 * 更新USER 个人信息
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUserInfo.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO updateUserInfo(TPermissionsStudentinfo info) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			vo = mobileApplication.updateUserInfo(info);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
	
	/**
	 * 论坛帖子发布
	 * @param bbs
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releasePost.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO releasePost(TBbsPost bbs,HttpServletRequest request) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			vo = mobileApplication.releasePost(bbs,fileMap);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
	
	/**
	 * 获取论坛类型
	 * @param bbs
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBbsType.do")
	@ResponseBody
	public MBaseVO getBbsType() throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			List<Map<String, Object>> bbsType = mobileApplication.getBbsType();
			 vo.setStatus(MBaseVO.STATE_SUCC);
			 vo.setData(bbsType);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
	
	
	/**
	 * 获取论坛所有帖
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBbsPost.do")
	@ResponseBody
	public MBaseVO getBbsPost() throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			List<Map<String, Object>> bbsType = mobileApplication.getBbsPost();
			 vo.setStatus(MBaseVO.STATE_SUCC);
			 vo.setData(bbsType);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
	
	
	/**
	 * 论坛帖子评论及回复
	 * 
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/replyBbs.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO replyBbs(TBbsPostReply reply) throws Exception {
		MBaseVO vo = null;
		try {
			vo = mobileApplication.replyPost(reply);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
	
	
	/**
	 * 获取帖子下评论及回复
	 * 
	 * @param course_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPostComment.do")
	@ResponseBody
	public MBaseVO getPostComment(String post_id) throws Exception {
		return mobileApplication.getPostComment(post_id);
	}
	
	/**
	 * 获取考试信息列表
	 * @param tNewExamInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectExamInfo.do")
	@ResponseBody
	public MBaseVO lookPost(TNewExamInfo tNewExamInfo) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			Map<String, Object> selectExamInfoByFilter = tNewexamInfoApplication.selectExamInfoByFilter(tNewExamInfo);
			vo.setData(selectExamInfoByFilter);
			vo.setStatus(MBaseVO.STATE_SUCC);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}

	/**
	 * 获取考试题
	 * @param tNewExamInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectPaperOptions.do")
	@ResponseBody
	public MBaseVO getPaperOptions(TNewExamInfo tNewExamInfo) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			List<Map<String, Object>> selectPaperOptionsByFilter = commonApplication.selectPaperOptionsByFilter(tNewExamInfo);
			vo.setData(selectPaperOptionsByFilter);
			vo.setStatus(MBaseVO.STATE_SUCC);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	/**
	 * 获取考试答案
	 * @param tNewExamInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectPaperAnswers.do")
	@ResponseBody
	public MBaseVO getPaperAnswers(TNewExamInfo tNewExamInfo) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			Map<String, Object> selectPaperAnswersByFilter = commonApplication.selectPaperAnswersByFilter(tNewExamInfo);
			vo.setData(selectPaperAnswersByFilter);
			vo.setStatus(MBaseVO.STATE_SUCC);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	/**
	 * 交卷
	 * @param tNewExamInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/handExamPaper.do")
	@ResponseBody
	public MBaseVO handExamPaper(String userId,String examId,String paperId,int continue_time,String jsonStr) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			Type type = new TypeToken<ArrayList<HashMap<String,String>>>() {}.getType();
			ArrayList<HashMap<String,String>> list = new Gson().fromJson(jsonStr,type);

			vo = mobileApplication.handExamPaper(userId, examId, paperId, continue_time, list);
			vo.setStatus(MBaseVO.STATE_SUCC);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	
	/**
	 * 开始答题
	 * @param remainingtime  examid paperid userid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/startExam.do")
	@ResponseBody
	public MBaseVO startExam(TNewexamDetails tNewexamDetails) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			vo = mobileApplication.startExam(tNewexamDetails);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	/**
	 * 保存标记信息
	 * @param tNewexamProcedure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveExamProblemState.do")
	@ResponseBody
	public MBaseVO saveExamProblemState(TNewexamProcedure tNewexamProcedure) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			int saveExamProblemState = tNewexamProcedureApplication.saveExamProblemState(tNewexamProcedure);
			if(saveExamProblemState>0){
				vo.setStatus(MBaseVO.STATE_SUCC);
			}else{
				vo.setStatus(MBaseVO.STATE_FAIL);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	/**
	 * 保存答案信息
	 * @param tNewexamProcedure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveExamQuestionAnswer.do")
	@ResponseBody
	public MBaseVO saveExamQuestionAnswer(TNewexamProcedure tNewexamProcedure) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			int saveExamQuestionAnswer = tNewexamProcedureApplication.saveExamQuestionAnswer(tNewexamProcedure);
			if(saveExamQuestionAnswer>0){
				vo.setStatus(MBaseVO.STATE_SUCC);
			}else{
				vo.setStatus(MBaseVO.STATE_FAIL);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	/**
	 * 保存人员考试基本信息
	 * @param tNewexamDetails
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveExamDetails.do")
	@ResponseBody
	public MBaseVO saveExamDetails(TNewexamDetails tNewexamDetails) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			int saveExamDetails = tNewexamDetailsApplication.saveExamDetails(tNewexamDetails);
			if(saveExamDetails>0){
				vo.setStatus(MBaseVO.STATE_SUCC);
			}else{
				vo.setStatus(MBaseVO.STATE_FAIL);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}
		return vo;
	}
	
	/**
	 * 浏览帖 浏览量+1
	 * @param post_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lookPost.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO lookPost(String post_id) throws Exception {
		MBaseVO vo = null;
		try {
			vo = mobileApplication.lookPost(post_id);
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
	/**
	 * 注册用户
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerUser.do", method = { RequestMethod.POST })
	@ResponseBody
	public MBaseVO registerUser(TPermissionsStudentinfo record) throws Exception {
		MBaseVO vo = new MBaseVO();
		try {
			Map<String, Object> registerUser = tPermissionsStudentinfoApplication.registerUser(record);
			if (registerUser != null && "1".equals(registerUser.get("flag"))) {
				vo.setStatus(MBaseVO.STATE_SUCC);
				vo.setMsg(registerUser.get("msg") != null ? registerUser.get("msg").toString() : "" );
			}else{
				vo.setMsg(registerUser.get("msg") != null ? registerUser.get("msg").toString() : "" );
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			vo.setStatus(MBaseVO.STATE_FAIL);
		}

		return vo;
	}
}