package com.zxtech.manager.application.app.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.app.TAppApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.util.Util;
import com.zxtech.ui.dao.app.TAppMapper;
import com.zxtech.ui.vo.app.TApp;

@Named
public class TAppApplicationImpl implements TAppApplication {
	
	@Inject
	private TAppMapper tAppMapper;

	@Override
	public PageInfo<Map<String, Object>> selectAppGroupByPlatformList(TApp record, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tAppMapper.selectAppGroupByPlatformList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}
	
	@Override
	public PageInfo<Map<String, Object>> selectAppList(TApp record, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tAppMapper.selectAppList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveApp(TApp record) throws Exception{
		try {
			//获取临时图片路径
			String tempPath = Util.readPropertiesValue("TempImagesPath");
			//app存储路径
			String appPath = Util.readPropertiesValue("appPath");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			
			if(record.getFilename() != null){
				File appFile = new File(appPath);
				if (!appFile.exists()) {
					appFile.mkdirs();
				}
				String filename = record.getFilename().replace("temp_", "");
				//后缀名
				String prefix = filename.substring(filename.lastIndexOf("."));
//				if(filename.endsWith(".ipa")){
					TApp param = new TApp();
					param.setPlatform(record.getPlatform());
					List<Map<String, Object>> list = tAppMapper.selectAppList(param);
					if(list.size()>0){
						Map<String, Object> m = list.get(0);
						String oldFileName = String.valueOf(m.get("filename"));
						File oldf = new File(appPath+"/"+oldFileName);
						String newFileName = filename.replace(prefix, "")+formatter.format(new Date())+prefix;
						File newf = new File(appPath+"/"+newFileName);
						//改名
						oldf.renameTo(newf);
						TApp updateApp = new TApp();
						updateApp.setFilename(newFileName);
						updateApp.setId(Integer.parseInt(m.get("id").toString()));
						tAppMapper.updateByPrimaryKeySelective(updateApp);
					}
//				}
				File srcFile = new File(tempPath+"/"+record.getFilename());
				File toFile = new File(appPath+"/"+filename);
				if(srcFile.exists()){
					//移动文件
					FileUtils.copyFile(srcFile, toFile);
					//删除临时文件
					srcFile.delete();
				}
				record.setFilename(filename);
			}
			record.setStatus(1);
			record.setCreateDate(new Date());
			record.setCreateUser(AuthDetailUtil.getLoginName());
			tAppMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteApp(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tAppMapper.deleteApp(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}
}
