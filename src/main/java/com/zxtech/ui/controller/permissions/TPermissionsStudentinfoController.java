package com.zxtech.ui.controller.permissions;

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

import com.zxtech.support.controller.Result;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.http.HttpUtil;
import com.zxtech.support.util.Util;
import com.zxtech.ui.application.permissions.TPermissionsStudentinfoApplication;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@RestController
@RequestMapping("/ui/tpermissionsstudentinfo")
public class TPermissionsStudentinfoController {
	@Autowired
	private TPermissionsStudentinfoApplication tPermissionsStudentinfoApplication;

	@RequestMapping("/deleteByPrimaryKey")
	public Result deleteByPrimaryKey(String studentid) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.deleteByPrimaryKey(studentid));
		return result;
	}
	@RequestMapping("/insert")
	public Result insert(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.insert(record));
		return result;
	}
	@RequestMapping("/insertSelective")
	public Result insertSelective(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.insertSelective(record));
		return result;
	}
	@RequestMapping("/selectByPrimaryKey")
	public Result selectByPrimaryKey(String studentid) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.selectByPrimaryKey(studentid));
		return result;
	}
	@RequestMapping("/updateByPrimaryKeySelective")
	public Result updateByPrimaryKeySelective(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.updateByPrimaryKeySelective(record));
		return result;
	}
	@RequestMapping("/updateByPrimaryKeyWithBLOBs")
	public Result updateByPrimaryKeyWithBLOBs(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.updateByPrimaryKeyWithBLOBs(record));
		return result;
	}
	@RequestMapping("/updateByPrimaryKey")
	public Result updateByPrimaryKey(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.updateByPrimaryKey(record));
		return result;
	}
	@RequestMapping("/selectLoginUser")
	public Result selectLoginUser() throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.selectLoginUser());
		return result;
	}
	
	/**
	 * 获取个人信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserInfo")
	public Result getUserInfo() throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.getUserInfo());
		return result;
	}
	
	/**
	 * 修改昵称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateNickName")
	public Result updateNickName(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.updateNickName(record));
		return result;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatePassword")
	public Result updatePassword(TPermissionsStudentinfo record) throws Exception{
		Result result = new Result();
		result.setFlag(HttpUtil.SUCCESS_FLG);
		result.setMsg("lms0002");
		result.setData(tPermissionsStudentinfoApplication.updatePassword(record));
		return result;
	}
	
	/**
	 * 上传头像
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadPhoto")
	@ResponseBody
	public Result uploadPhoto(@RequestParam(value = "file") MultipartFile[] file, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resuMap  = new HashMap<>();
		Result result = new Result();
		if (file.length < 1) {
			result.setFlag(HttpUtil.SUCCESS_FLG);
			result.setMsg("请选择文件");
		} else {
			try {
				String userPhotoPath = Util.readPropertiesValue("UserPhotoPath");
				String timestamp = String.valueOf(System.currentTimeMillis());
				String fileName = file[0].getOriginalFilename();
				fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + timestamp + fileName.substring(fileName.lastIndexOf("."));
				String tempFilePath = userPhotoPath + "/" + fileName;
				// 初始化文件
				File decorateFile = new File(tempFilePath);
				// 路径为文件且不为空则进行删除
				if (decorateFile.isFile() && decorateFile.exists()) {
					decorateFile.delete();
				}
				// 进行文件保存
				FileUtils.copyInputStreamToFile(file[0].getInputStream(), new File(userPhotoPath, fileName));
				
				// 修改头像
				tPermissionsStudentinfoApplication.updatePhotoName(fileName);
				
				resuMap.put("imageName", fileName);
				result.setData(resuMap);
				result.setFlag(HttpUtil.SUCCESS_FLG);
				result.setMsg("文件上传成功!");
			} catch (Exception e) {
				throw new BusinessException(e, "文件上传失败!");
			}
		}
		return result;
	}
}
