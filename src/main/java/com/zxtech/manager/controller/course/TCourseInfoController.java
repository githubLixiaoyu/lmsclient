package com.zxtech.manager.controller.course;

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

import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.course.TCourseInfoApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.vo.app.TCourseInfoWithBLOBs;

@RestController
@RequestMapping("/manager/tcourseinfo")
public class TCourseInfoController {
	
	@Autowired
	private TCourseInfoApplication tCourseInfoApplication;

	@RequestMapping("/selectCourseInfoList")
	public Result selectCourseInfoList(TCourseInfoWithBLOBs record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		record.setStaffid(AuthDetailUtil.getLoginUserId());
		PageInfo<Map<String, Object>> page = tCourseInfoApplication.selectCourseInfoList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
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
//					tempPath = request.getSession().getServletContext().getRealPath("temp");
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
	
	@RequestMapping("/saveCourseInfo")
	public Result saveCourseInfo(TCourseInfoWithBLOBs record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseInfoApplication.saveCourseInfo(record));
		return result;
	}
	
	@RequestMapping("/deleteCourseInfo")
	public Result deleteCourseInfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseInfoApplication.deleteCourseInfo(idList));
		return result;
	}
	
	@RequestMapping("/sendCourseInfo")
	public Result sendCourseInfo(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tCourseInfoApplication.sendCourseInfo(idList));
		return result;
	}
	
}
