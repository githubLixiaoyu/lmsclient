package com.zxtech.manager.application.course.impl;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.course.TCourseImageApplication;
import com.zxtech.manager.application.course.TCourseInfoApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.util.Util;
import com.zxtech.ui.dao.app.TCourseInfoMapper;
import com.zxtech.ui.dao.course.TCourseImageMapper;
import com.zxtech.ui.vo.app.TCourseInfo;
import com.zxtech.ui.vo.app.TCourseInfoWithBLOBs;
import com.zxtech.ui.vo.course.TCourseImage;

@Named
public class TCourseInfoApplicationImpl implements TCourseInfoApplication{
	@Inject
	private TCourseInfoMapper tCourseInfoMapper;
	@Inject
	private TCourseImageMapper tCourseImageMapper;
	
	@Autowired
	private TCourseImageApplication tCourseImageApplication;
	
	private static final int BUFFER_SIZE = 16 * 1024;//16M

	@Override
	public PageInfo<Map<String, Object>> selectCourseInfoList(TCourseInfoWithBLOBs record, int pageNum, int pageSize) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//项目名
		String path = request.getContextPath();
		String imageDirpath = "lmsFiles/";
		//工程访问路径
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		//web端图片存储路径
		String scormImagesPath = Util.readPropertiesValue("ScormImagesPath");
		scormImagesPath = scormImagesPath.substring(scormImagesPath.lastIndexOf("//")+2);
		//移动端图片存储路径
		String scormMobileImagesPath = Util.readPropertiesValue("ScormImagesPath");
		scormMobileImagesPath = scormMobileImagesPath.substring(scormMobileImagesPath.lastIndexOf("//")+2);
		//默认图片路径
		String defaultPath = basePath+path+"/images/noimage.jpg";
		
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tCourseInfoMapper.selectCourseInfoList(record);
		for(int i=0;i<result.size();i++){
			Map<String, Object> m = result.get(i);
			if(m.get("imagename") == null || m.get("imagename").equals("")){
				m.put("imagepath", defaultPath);
			}else{
				m.put("imagepath", basePath + imageDirpath + scormImagesPath + "/" + m.get("imagename"));
			}
			if(m.get("imagemobilename") == null || m.get("imagemobilename").equals("")){
				m.put("imagemobilepath", defaultPath);
			}else{
				m.put("imagemobilepath", basePath + imageDirpath + scormMobileImagesPath + "/" + m.get("imagemobilename"));
			}
		}
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveCourseInfo(TCourseInfoWithBLOBs record) throws Exception{
		try {
			//获取临时图片路径
			String tempPath = Util.readPropertiesValue("TempImagesPath");
			//web端图片存储路径
			String scormImagesPath = Util.readPropertiesValue("ScormImagesPath");
			//移动端图片存储路径
			String scormMobileImagesPath = Util.readPropertiesValue("ScormImagesPath");
			Map<String,Object> tCourseInfoMap = null;
			if(record != null && record.getCourseid() != null){
				tCourseInfoMap = tCourseInfoMapper.selectByPrimaryKey(record.getCourseid());
			}
			
			if(!"".equals(record.getImagename())){
				if(tCourseInfoMap == null || tCourseInfoMap.get("imagename") == null || !tCourseInfoMap.get("imagename").equals(record.getImagename())){
					File scormImagesFile = new File(scormImagesPath);
					if (!scormImagesFile.exists()) {
						scormImagesFile.mkdirs();
					}
					String imagename = record.getImagename().replace("temp_", "");
					File srcFile = new File(tempPath+"/"+record.getImagename());
					File imageFile = new File(scormImagesPath+"/"+imagename);
					if(srcFile.exists()){
						//移动图片
						FileUtils.copyFile(srcFile, imageFile);
						//删除临时图片
						srcFile.delete();
					}
					record.setImagename(imagename);
				}
			}
			if(!"".equals(record.getImagemobilename())){
				if(tCourseInfoMap == null || tCourseInfoMap.get("imagemobilename") == null || !tCourseInfoMap.get("imagemobilename").equals(record.getImagemobilename())){
					File scormImagesFile = new File(scormMobileImagesPath);
					if (!scormImagesFile.exists()) {
						scormImagesFile.mkdirs();
					}
					String imagemobilename = record.getImagemobilename().replace("temp_", "");
					File srcFile = new File(tempPath+"/"+record.getImagemobilename());
					File imageFile = new File(scormMobileImagesPath+"/"+imagemobilename);
					if(srcFile.exists()){
						FileUtils.copyFile(srcFile, imageFile);
						srcFile.delete();
					}
					record.setImagemobilename(imagemobilename);
				}
			}
			
			if (!record.getCourseuploadtype().equals("2")) {
				//存放上传课件(zip)的目录
				String ScormZipPath = Util.readPropertiesValue("ScormZipPath");
				File ScormZipFile = new File(ScormZipPath);
				if (!ScormZipFile.exists()) {
					ScormZipFile.mkdirs();
				}
				
				//存放上传课件的目录
				String ScormContentsPath = Util.readPropertiesValue("ScormContentsPath");
				File ScormContentsFile = new File(ScormContentsPath);
				if (!ScormContentsFile.exists()) {
					ScormContentsFile.mkdirs();
				}
				
				String fileScormName = record.getFileScormName();
				if(fileScormName.indexOf("temp_") > -1){
					int lastIndex = fileScormName.replace("temp_", "").lastIndexOf(".");
					String courseFolder = fileScormName.replace("temp_", "").substring(0, lastIndex);
					
					String filePath = ScormZipPath+"\\" + record.getFileScormName();
					File file = new File(filePath); 
					
					if (!file.getParentFile().exists()) {
						if (file.getParentFile().mkdir()) {
							File srcFile = new File(tempPath+"/"+record.getFileScormName());
							File zipFile = new File(ScormZipPath+"/"+fileScormName.replace("temp_", ""));
							if(srcFile.exists()){
								FileUtils.copyFile(srcFile, zipFile);
							}
						}
					} else {
						File srcFile = new File(tempPath+"/"+record.getFileScormName());
						File zipFile = new File(ScormZipPath+"/"+fileScormName.replace("temp_", ""));
						if(srcFile.exists()){
							FileUtils.copyFile(srcFile, zipFile);
						}
					}
					File folderFile = new File(tempPath+"/"+record.getFileScormName());
					if(folderFile.exists()){
						folderFile.delete();
					}
					
					//解压zip文件
					BufferedOutputStream dest = null;
					FileInputStream fis = new FileInputStream(filePath.replace("temp_", ""));
					ZipInputStream zis = new ZipInputStream(
							new BufferedInputStream(fis));
					ZipEntry entry; 
					while ((entry = zis.getNextEntry()) != null) {
					    int len = 0; 
						byte data[] = new byte[BUFFER_SIZE];
						if (!entry.isDirectory()) {
							String filename = entry.getName();
							filename = filename.replace('/',
									java.io.File.separatorChar);

							filename = ScormContentsFile + "\\" + courseFolder + "\\" + filename;
							java.io.File destFile = new java.io.File(filename);

							String parent = destFile.getParent();
							if (parent != null) {
								java.io.File parentFile = new java.io.File(parent);
								if (!parentFile.exists()) {
									parentFile.mkdirs();
								}
							}
							OutputStream outStream = new FileOutputStream(filename);
							dest = new BufferedOutputStream(outStream, BUFFER_SIZE);
							while ((len = zis.read(data, 0, BUFFER_SIZE)) != -1) {
								dest.write(data, 0, len);
							}
							
							dest.flush();
							dest.close();
							outStream.close();
						}
					}
					zis.close();
					
					record.setStandby1(record.getFileScormName().replace("temp_", ""));
				}
				
				
			} 
			
			if(record.getCourseid() == null || "".equals(record.getCourseid())){
				record.setCourseid(UUID.randomUUID().toString());
				record.setCreatetime(new Date());
				record.setStaffid(AuthDetailUtil.getLoginUserId()); 
				record.setControl("choice");
				record.setActive("0");
				record.setAuthorizedvisible("0");
				record.setZannum("0");
				//插入
				tCourseInfoMapper.insertSelective(record);
			}else{
				//修改
				tCourseInfoMapper.updateByPrimaryKeySelective(record);
			}
			saveCourseImage(record);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public void saveCourseImage(TCourseInfo record) throws Exception{//web端图片存储路径
		String scormImagesPath = Util.readPropertiesValue("ScormImagesPath");
		TCourseImage courseImage = new TCourseImage();
		Map<String, Object> m = tCourseImageApplication.selectTCourseImageByCourseid(record.getCourseid());
		if(m == null){
			UUID uuid2 = UUID.randomUUID();
			courseImage.setId(uuid2.toString());
		}
		if(!"".equals(record.getImagename())){
			String imgName = record.getImagename().replace("temp_", "");
			courseImage.setImgname(imgName);
			courseImage.setImgtype(imgName.substring(imgName.lastIndexOf(".")));
		}
		if(!"".equals(record.getImagemobilename())){
			String imagemobilename = record.getImagemobilename().replace("temp_", "");
			courseImage.setImgmobilename(imagemobilename);
		}
		courseImage.setId(record.getImageId());
		courseImage.setCourseid(record.getCourseid());
		courseImage.setImgpath(scormImagesPath);
		courseImage.setType("0");
		if(m == null){
			courseImage.setId(UUID.randomUUID().toString());
			tCourseImageMapper.insertSelective(courseImage);
		}else{
			tCourseImageMapper.updateByPrimaryKeySelective(courseImage);
		}
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteCourseInfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tCourseInfoMapper.deleteCourseInfo(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int sendCourseInfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			TCourseInfoWithBLOBs record = new TCourseInfoWithBLOBs();
			record.setReleaser(AuthDetailUtil.getLoginUserId());
			for(int i=0;i<ids.size();i++){
				record.setActive("6");
				record.setCourseid(ids.get(i));
				tCourseInfoMapper.sendCourseInfo(record);
			}
			return idList.length; 
		} catch (Exception e) {
			throw new BusinessException(e,"发布失败!");
		}
	}
}