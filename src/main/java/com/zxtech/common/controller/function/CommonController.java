package com.zxtech.common.controller.function;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.common.application.function.CommonApplication;
import com.zxtech.manager.application.newtestpaper.TNewtestpaperCategoryApplication;
import com.zxtech.manager.application.permissions.TLoginInfoApplication;
import com.zxtech.manager.application.permissions.TPermissionsStudentDepartApplication;
import com.zxtech.manager.application.question.TKnowledgepointApplication;
import com.zxtech.manager.application.question.TQuestionCategoryApplication;
import com.zxtech.manager.application.question.TQuestionDifficultyApplication;
import com.zxtech.manager.application.question.TQuestionstypeApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.support.util.Util;
import com.zxtech.teacher.application.question.TQuestionsinfoApplication;
import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;
import com.zxtech.ui.vo.newtestpaper.TNewtestpaperCategory;
import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;
import com.zxtech.ui.vo.question.TKnowledgepoint;
import com.zxtech.ui.vo.question.TQuestionCategory;
import com.zxtech.ui.vo.question.TQuestionDifficulty;
import com.zxtech.ui.vo.question.TQuestionsinfo;
import com.zxtech.ui.vo.question.TQuestionstype;

@RestController
@RequestMapping("/auth/common")
public class CommonController {
	
	@Autowired
	private CommonApplication commonApplication;
	@Autowired
	private TQuestionstypeApplication tQuestionstypeApplication;
	@Autowired
	private TQuestionDifficultyApplication tQuestionDifficultyApplication;
	@Autowired
	private TQuestionCategoryApplication tQuestionCategoryApplication;
	@Autowired
	private TKnowledgepointApplication tKnowledgepointApplication;
	@Autowired
	private TNewtestpaperCategoryApplication tNewtestpaperCategoryApplication;
	@Autowired
	private TQuestionsinfoApplication tQuestionsinfoApplication;
	@Autowired
	private TPermissionsStudentDepartApplication tPermissionsStudentDepartApplication;
	@Autowired
	private TLoginInfoApplication tLoginInfoApplication;

	/**
	 * 查询功能数菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getFunctionMenu")
	public Result getFunctionMenu() throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(commonApplication.getFunctionMenu());
		return result;
	}

	/**
	 * 查询个人信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserInfo")
	public Result getUserInfo() throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(AuthDetailUtil.getLoginInfo());
		return result;
	}
	
	@RequestMapping("/selectOptionQuestionstypeList")
	public Result selectOptionQuestionstypeList(TQuestionstype record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tQuestionstypeApplication.selectOptionQuestionstypeList(record);
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/selectOptionQuestionDifficultyList")
	public Result selectOptionQuestionDifficultyList(TQuestionDifficulty record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tQuestionDifficultyApplication.selectOptionQuestionDifficultyList(record);
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/selectNewtestpaperCategoryList")
	public Result selectNewtestpaperCategoryList(TNewtestpaperCategory record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tNewtestpaperCategoryApplication.selectNewtestpaperCategoryList(record);
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/selectOptionQuestionCategoryList")
	public Result selectOptionQuestionCategoryList(TQuestionCategory record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tQuestionCategoryApplication.selectOptionQuestionCategoryList(record);
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/selectOptionKnowledgepointList")
	public Result selectOptionKnowledgepointList(TKnowledgepoint record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tKnowledgepointApplication.selectOptionKnowledgepointList(record);
		result.setData(list);
		return result;
	}
	
	/**
	 * 上传至临时文件
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadImageToTemp")
	@ResponseBody
	public Result uploadImageToTemp(@RequestParam(value = "file") MultipartFile[] file, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resuMap  = new HashMap<>();
		List<String> list = new ArrayList<>();
		Result result = new Result();
		if (file.length < 0 || file.length == 0) {
			result.setFlag(HttpUtil.SUCCESS_FLG);
			result.setMsg("请选择文件");
		} else {
			try {
//				String path = request.getContextPath();
				String path = "/lmsFiles";
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ path + "/";
				String tempPath = "";
				String fileName = "";
				String tempFilePath = "";
				for (int i = 0; i < file.length; i++) {
					// 临时图片保存的路径
					tempPath = Util.readPropertiesValue("TempImagesPath");
					File scormImagesFile = new File(tempPath);
					if (!scormImagesFile.exists()) {
						scormImagesFile.mkdirs();
					}
					//删除临时文件
					String tempName = request.getParameter("tempName");
					File tempFile = new File(tempPath+"/"+tempName);
					if(tempFile.exists()){
						tempFile.delete();
					}
					// 获得文件名
					fileName = file[i].getOriginalFilename();
					fileName = "temp_"+UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
					tempFilePath = tempPath + "/" + fileName;
					// 初始化文件
					File decorateFile = new File(tempFilePath);
					// 路径为文件且不为空则进行删除
					if (decorateFile.isFile() && decorateFile.exists()) {
						decorateFile.delete();
					}
					// 进行文件保存
					FileUtils.copyInputStreamToFile(file[i].getInputStream(), new File(tempPath, fileName));
					tempPath = tempPath.substring(tempPath.lastIndexOf("//")+2);
					list.add(basePath + tempPath +"/" + fileName);
				}
				resuMap.put("imagesSrc", list);
				resuMap.put("fileName", fileName);
				result.setData(resuMap);
				result.setFlag(HttpUtil.SUCCESS_FLG);
				result.setMsg("文件上传成功!");
			} catch (Exception e) {
				throw new BusinessException(e, "文件上传失败!");
			}
		}
		return result;
	}
	
	/**
	 * 根据试卷ID检索试卷内容
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPaperInfoById")
	public Result getPaperInfoById(TNewTestpaperInfo tNewTestpaperInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(commonApplication.getPaperInfoById(tNewTestpaperInfo));
		return result;
	}
	
	/**
	 * 根据考试ID和用户ID检索试卷内容
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectPaperOptionsByFilter")
	public Result selectPaperOptionsByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(commonApplication.selectPaperOptionsByFilter(tNewExamInfo));
		return result;
	}
	
	/**
	 * 根据考试ID检索试卷答案
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectPaperAnswersByFilter")
	public Result selectPaperAnswersByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(commonApplication.selectPaperAnswersByFilter(tNewExamInfo));
		return result;
	}
	
	@RequestMapping("/deleteTempImage")
	public Result deleteTempImage(String tempFileName) throws Exception{
		String tempPath = Util.readPropertiesValue("TempImagesPath");
		File tempFile = new File(tempPath+"/"+tempFileName);
		if(tempFile.exists()){
			tempFile.delete();
		}
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		return result;
	}
	
	@RequestMapping("/selectQuestionNumByType")
	public Result selectQuestionNumByType(TQuestionsinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		Map<String, Integer> map = tQuestionsinfoApplication.selectQuestionNumByType(record);
		result.setData(map);
		return result;
	}
	
	/**
	 * 查询部门
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectOptionDepartList")
	public Result selectOptionDepartList(TPermissionsStudentDepart record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		List<Map<String, Object>> list = tPermissionsStudentDepartApplication.selectAllPermissionsStudentDepartList(record);
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/saveLoginInfo")
	public Result saveLoginInfo() throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tLoginInfoApplication.saveLoginInfo());
		return result;
	}
	/**
	 * 根据考试ID和用户ID检索Vr考试结果 2017-09-20
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectVrAnswersByFilter")
	public Result selectVrOptionsByFilter(TNewExamInfo tNewExamInfo) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(commonApplication.selectVrAnswersByFilter(tNewExamInfo));
		return result;
	}
}
