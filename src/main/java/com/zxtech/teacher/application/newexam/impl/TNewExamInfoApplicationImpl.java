package com.zxtech.teacher.application.newexam.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.util.Util;
import com.zxtech.teacher.application.newexam.TNewExamInfoApplication;
import com.zxtech.ui.dao.newexam.TNewExamInfoMapper;
import com.zxtech.ui.dao.newexam.TNewexamAuthoruserMapper;
import com.zxtech.ui.dao.newexam.TNewexamImageMapper;
import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newexam.TNewexamImage;

@Named
public class TNewExamInfoApplicationImpl implements TNewExamInfoApplication{
	@Inject
	private TNewExamInfoMapper tNewExamInfoMapper;
	@Inject
	private TNewexamImageMapper tNewexamImageMapper;
	@Inject
	private TNewexamAuthoruserMapper tNewexamAuthoruserMapper;

	@Override
	public PageInfo<Map<String, Object>> selectNewExamInfoList(TNewExamInfo record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		List<Map<String, Object>> list = tNewExamInfoMapper.selectNewExamInfoList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveNewExamInfo(TNewExamInfo record) throws Exception{
		try{
			boolean updateFlag = true;
			String examid = record.getExamid();
			if(examid == null || "".equals(examid)){
				examid = UUID.randomUUID().toString();
				updateFlag = false;
			}
			record.setExamid(examid);
			if(updateFlag){
				tNewExamInfoMapper.updateByPrimaryKeySelective(record);
			}else{
				record.setCreatortime(new Date());
				record.setStatus("0");
				record.setIfappend("0");
				record.setCreatorid(AuthDetailUtil.getLoginUserId());
				tNewExamInfoMapper.insertSelective(record);
			}
			
			
			//先删除
			tNewexamImageMapper.deleteByExamid(examid);
			//添加封图
			if(record.getImagename().indexOf("temp") > -1) {
				TNewexamImage examImage = new TNewexamImage();
				examImage.setId(UUID.randomUUID().toString());
				
				//获取临时图片路径
				String tempPath = Util.readPropertiesValue("TempImagesPath");
				//web端图片存储路径
				String examImagesPath = Util.readPropertiesValue("ExamImagesPath");
				
				File examImagesFile = new File(examImagesPath);
				if (!examImagesFile.exists()) {
					examImagesFile.mkdirs();
				}
				String imagename = record.getImagename().replace("temp_", "");
				File srcFile = new File(tempPath+"/"+record.getImagename());
				File imageFile = new File(examImagesPath+"/"+imagename);
				if(srcFile.exists()){
					//移动图片
					FileUtils.copyFile(srcFile, imageFile);
					//删除临时图片
					srcFile.delete();
				}
				
				String migPath = record.getImagename();
				examImage.setImgname(imagename);
				examImage.setImgtype(migPath.substring(migPath.lastIndexOf(".")));
				examImage.setImgpath(migPath);
				examImage.setExamid(examid);
				examImage.setType("0");
				tNewexamImageMapper.insertSelective(examImage);
			}else{
//				//web端图片存储路径
//				String examImagesPath = Util.readPropertiesValue("ExamImagesPath");
//				File srcFile = new File(examImagesPath+"/"+record.getImagename());
//				if(srcFile.exists()){
//					//删除图片
//					srcFile.delete();
//				}
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteNewExamInfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			tNewexamAuthoruserMapper.deleteNewexamAuthoruser(ids);
			tNewexamImageMapper.deleteNewexamImage(ids);
			tNewExamInfoMapper.deleteNewExamInfo(ids);
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

	@Override
	public Map<String, Object> selectNewExamInfoById(String examid) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		TNewExamInfo record = new TNewExamInfo();
		record.setExamid(examid);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		Map<String, Object> data = new HashMap<String, Object>();
		if(!"".equals(examid)){
			List<Map<String, Object>> list = tNewExamInfoMapper.selectNewExamInfoList(record);
			if(list.size() > 0){
				//工程访问路径
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
				//项目名
				String path = request.getContextPath();
				String imageDirpath = "lmsFiles/";
				//默认图片路径
				String defaultPath = basePath+path+"/images/noimage.jpg";
				//web端图片存储路径
				String examImagesPath = Util.readPropertiesValue("ExamImagesPath");
				examImagesPath = examImagesPath.substring(examImagesPath.lastIndexOf("//")+2);
				Map<String, Object> examinfo = list.get(0);
				if(examinfo.get("imgName") != null && !"".equals(examinfo.get("imgName"))){
					examinfo.put("imagepath", basePath + imageDirpath + examImagesPath + "/" + examinfo.get("imgName"));
				}else{
					examinfo.put("imagepath", defaultPath);
				}
				data.put("examinfo", examinfo);
			}
		}
		return data;
	}

	@Override
	public int sendNewExamInfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tNewExamInfoMapper.sendNewExamInfo(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"发布失败!");
		}
	}
		
}
