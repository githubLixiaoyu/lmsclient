package com.zxtech.manager.controller.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.app.TAppApplication;
import com.zxtech.support.controller.Result;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.vo.app.TApp;

@RestController
@RequestMapping("/manager/tapp")
public class TAppController {
	
	@Autowired
	private TAppApplication tAppApplication;

	@RequestMapping("/selectAppGroupByPlatformList")
	public Result selectAppGroupByPlatformList(TApp record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tAppApplication.selectAppGroupByPlatformList(record, start / length + 1, length);
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
	@RequestMapping("/selectAppList")
	public Result selectAppList(TApp record, int start, int length) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		PageInfo<Map<String, Object>> page = tAppApplication.selectAppList(record, start / length + 1, length);
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
	@RequestMapping("/uploadToTemp")
	@ResponseBody
	public Result uploadToTemp(@RequestParam(value = "file") MultipartFile[] file, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resuMap  = new HashMap<>();
		Result result = new Result();
		if (file.length < 0 || file.length == 0) {
			result.setFlag(HttpUtil.SUCCESS_FLG);
			result.setMsg("请选择文件");
		} else {
			try {
				String tempPath = "";
				String fileName = "";
				String tempFilePath = "";
				for (int i = 0; i < file.length; i++) {
					// 临时保存的路径
					tempPath = Util.readPropertiesValue("TempImagesPath");
					File scormImagesFile = new File(tempPath);
					if (!scormImagesFile.exists()) {
						scormImagesFile.mkdirs();
					}
					// 获得文件名
					fileName = file[i].getOriginalFilename();
//					if(fileName.endsWith(".ipa")){
						fileName = "temp_"+fileName;
//					}else{
//						fileName = "temp_"+UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
//					}
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
				}
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
	
	@RequestMapping("/saveApp")
	public Result saveApp(TApp record) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tAppApplication.saveApp(record));
		return result;
	}
	
	@RequestMapping("/deleteApp")
	public Result deleteApp(String[] idList) throws Exception {
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tAppApplication.deleteApp(idList));
		return result;
	}
	
}
