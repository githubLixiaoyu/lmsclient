package com.zxtech.manager.application.course.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zxtech.manager.application.course.TCourseTypeApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.util.Util;
import com.zxtech.ui.dao.app.TCourseTypeMapper;
import com.zxtech.ui.vo.app.TCourseType;

@Named
public class TCourseTypeApplicationImpl implements TCourseTypeApplication{
	@Inject
	private TCourseTypeMapper tCourseTypeMapper;

	@Override
	public PageInfo<Map<String, Object>> selectCourseTypeList(TCourseType record, int pageNum, int pageSize) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//项目名
		String path = request.getContextPath();
		String imageDirpath = "lmsFiles/";
		//工程访问路径
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		//web端图片存储路径
		String scormImagesPath = Util.readPropertiesValue("CourseTypeImagesPath");
		scormImagesPath = scormImagesPath.substring(scormImagesPath.lastIndexOf("//")+2);
		//移动端图片存储路径
		String scormMobileImagesPath = Util.readPropertiesValue("CourseTypeImagesPath");
		scormMobileImagesPath = scormMobileImagesPath.substring(scormMobileImagesPath.lastIndexOf("//")+2);
		//默认图片路径
		String defaultPath = basePath+path+"/images/noimage.jpg";
		
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tCourseTypeMapper.selectCourseTypeList(record);
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
	public int saveCourseType(TCourseType record) throws Exception{
		try {
			if(record.getParentid() != 0){
				//获取临时图片路径
				String tempPath = Util.readPropertiesValue("TempImagesPath");
				//web端图片存储路径
				String scormImagesPath = Util.readPropertiesValue("CourseTypeImagesPath");
				//移动端图片存储路径
				String scormMobileImagesPath = Util.readPropertiesValue("CourseTypeImagesPath");
				Map<String,Object> tCourseTypeMap = null;
				if(record != null && record.getId() != null){
					tCourseTypeMap = tCourseTypeMapper.selectByPrimaryKey((long)record.getId());
				}
				if(tCourseTypeMap == null || tCourseTypeMap.get("imagename") == null || !tCourseTypeMap.get("imagename").equals(record.getImagename())){
					File scormImagesFile = new File(scormImagesPath);
					if (!scormImagesFile.exists()) {
						scormImagesFile.mkdirs();
					}
					String imagename = record.getImagename().replace("temp_", "");
					File srcFile = new File(tempPath+"/"+record.getImagename());
					File imageFile = new File(scormImagesPath+"/"+imagename);
					//移动图片
					FileUtils.copyFile(srcFile, imageFile);
					//删除临时图片
					srcFile.delete();
					record.setImagename(imagename);
				}
				if(tCourseTypeMap == null || tCourseTypeMap.get("imagemobilename") == null || !tCourseTypeMap.get("imagemobilename").equals(record.getImagemobilename())){
					File scormImagesFile = new File(scormMobileImagesPath);
					if (!scormImagesFile.exists()) {
						scormImagesFile.mkdirs();
					}
					String imagemobilename = record.getImagemobilename().replace("temp_", "");
					File srcFile = new File(tempPath+"/"+record.getImagemobilename());
					File imageFile = new File(scormMobileImagesPath+"/"+imagemobilename);
					FileUtils.copyFile(srcFile, imageFile);
					srcFile.delete();
					record.setImagemobilename(imagemobilename);
				}
			}
			
			record.setRemoved(0);
			record.setUpdatetime(new Date());
			if(record.getId() == null){
				record.setCreatetime(new Date());
//				Map<String, Object> m = tCourseTypeMapper.selectTopSort(record);
//				int sort = 0;
//				if(m != null){
//					sort = Integer.parseInt(m.get("sort").toString())+1;
//				}
//				record.setSort(sort);
				//插入
				tCourseTypeMapper.insertSelective(record);
			}else{
				//修改
				tCourseTypeMapper.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteCourseType(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tCourseTypeMapper.deleteCourseType(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除分类类型失败!");
		}
	}

	@Override
	public List<Map<String, Object>> selectCourseTypeListById(int parentid) throws Exception{
		TCourseType record = new TCourseType();
		record.setParentid(parentid);
		List<Map<String, Object>> list = tCourseTypeMapper.selectCourseTypeList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("id"));
			String coursetypename = String.valueOf(map.get("coursetypename"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", coursetypename);
			returnList.add(m);
		}
		return returnList;
	}
}