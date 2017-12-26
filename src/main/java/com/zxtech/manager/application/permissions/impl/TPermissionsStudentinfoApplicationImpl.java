package com.zxtech.manager.application.permissions.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.permissions.TPermissionsStudentinfoApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.md5.SecurityMD5;
import com.zxtech.support.util.Util;
import com.zxtech.ui.dao.permissions.TPermissionsStudentRoleMapper;
import com.zxtech.ui.dao.permissions.TPermissionsStudentinfoMapper;
import com.zxtech.ui.vo.permissions.TPermissionsStudentRole;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@Named
public class TPermissionsStudentinfoApplicationImpl implements TPermissionsStudentinfoApplication{
	@Inject
	private TPermissionsStudentinfoMapper tPermissionsStudentinfoMapper;
	@Inject
	private TPermissionsStudentRoleMapper tPermissionsStudentRoleMapper;

	@Override
	public PageInfo<Map<String, Object>> selectPermissionsStudentinfoList(TPermissionsStudentinfo record, int pageNum,
			int pageSize) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//项目名
		String path = request.getContextPath();
		String imageDirpath = "lmsFiles/";
		//工程访问路径
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		//web端图片存储路径
		String scormImagesPath = Util.readPropertiesValue("UserPhotoPath");
		scormImagesPath = scormImagesPath.substring(scormImagesPath.lastIndexOf("//")+2);
		//默认图片路径
		String defaultPath = basePath+path+"/images/noimage.jpg";
		PageHelper.startPage(pageNum, pageSize);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tPermissionsStudentinfoMapper.selectTPermissionsStudentinfoList(record);
		for(int i=0;i<result.size();i++){
			Map<String, Object> m = result.get(i);
			if(m.get("photoname") == null || m.get("photoname").equals("")){
				m.put("imagepath", defaultPath);
			}else{
				m.put("imagepath", basePath + imageDirpath + scormImagesPath + "/" + m.get("photoname"));
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
	public int savePermissionsStudentinfo(TPermissionsStudentinfo record) throws Exception{
		try{
			if(record.getStudentid() != null && !"".equals(record.getStudentid())){
				if(record.getPassword() != null){
					record.setPassword(SecurityMD5.md5s(record.getPassword()));
				}
				return tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(record);
			}else{
				UUID uuid = UUID.randomUUID();
				record.setStudentid(uuid.toString());
				record.setCreatetime(new Date());
				record.setPassword(SecurityMD5.md5s(record.getPassword()));
				record.setStafflevel("0");
				record.setLoginnumber(0);
				record.setCode(record.getStudentid().substring(5));
				tPermissionsStudentinfoMapper.insertSelective(record);
				
				TPermissionsStudentRole tPermissionsStudentRole = new TPermissionsStudentRole();
				tPermissionsStudentRole.setRoleid(2);
				tPermissionsStudentRole.setStudentid(uuid.toString());
				tPermissionsStudentRoleMapper.insert(tPermissionsStudentRole);
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deletePermissionsStudentinfo(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tPermissionsStudentinfoMapper.deleteTPermissionsStudentinfo(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除人员失败!");
		}
	}

	@Override
	public Map<String, Object> findUserByLoginCode(String logincode) throws Exception{
		TPermissionsStudentinfo record = new TPermissionsStudentinfo();
		record.setLogincode(logincode);
		List<Map<String, Object>> list = tPermissionsStudentinfoMapper.selectTPermissionsStudentinfoAllList(record);
		if(list != null && list.size() >0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean valideLoginCode(TPermissionsStudentinfo record) throws Exception{
		Map<String, Object> m = findUserByLoginCode(record.getLogincode());
		if(m == null || (record.getLogincode().equals(m.get("logincode")) && record.getStudentid().equals(m.get("studentid")))){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Map<String, Object> registerUser(TPermissionsStudentinfo record) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		//注册失败
		data.put("flag", "0");
		String msg = "";
		String msg_en = "";
		//姓名
		String name = record.getName();
		//用户名
		String logincode = record.getLogincode();
		//昵称
		String nickname = record.getName();
//		//昵称 2017.09.25
//		String nickname = record.getNickname();
		//密码
		String password = record.getPassword();
		//电话
		String tel = record.getTel();
		//公司
		String standby1 = record.getStandby1();
		
		if("".equals(name)){
			msg = "姓名为空";
			data.put("msg", msg);
			msg_en = "Name is empty!";
			data.put("msg_en", msg_en);
			return data;
		}else{
			if(name.length() > 20){
				msg = "姓名长度超过20";
				data.put("msg", msg);
				msg_en = "Name Had Beyonded 20";
				data.put("msg_en", msg_en);
				return data;
			}
		}
		
		if("".equals(logincode)){
			msg = "登录账号为空";
			data.put("msg", msg);
			msg_en = "Login Account is empty";
			data.put("msg_en", msg_en);
			return data;
		}else{
			if(logincode.length() > 20){
				msg = "登录账号长度超过20";
				data.put("msg", msg);
				msg_en = "Login Account Had Beyonded 20";
				data.put("msg_en", msg_en);
				return data;
			}else{
				Map<String, Object> m = findUserByLoginCode(logincode);
				if(m != null){
					msg = "登录账号已经存在";
					data.put("msg", msg);
					msg_en = "Login Account is Existing";
					data.put("msg_en", msg_en);
					return data;
				}
			}
		}
		
		if("".equals(nickname)){
			msg = "昵称为空";
			data.put("msg", msg);
			msg_en = "Nickname is empty";
			data.put("msg_en", msg_en);
			return data;
		}else{
			if(nickname.length() > 20){
				msg = "昵称长度超过20";
				data.put("msg", msg);
				msg_en = "Nickname Had Beyonded 20";
				data.put("msg_en", msg_en);
				return data;
			}
		}
		
		if("".equals(password)){
			msg = "密码为空";
			data.put("msg", msg);
			msg_en = "The password is empty";
			data.put("msg_en", msg_en);
			return data;
		}else{
			if(password.length() > 20){
				msg = "密码长度超过20";
				data.put("msg", msg);
				msg_en = "Password Had Beyonded 20";
				data.put("msg_en", msg_en);
				return data;
			}
		}
		
		if("".equals(tel)){
			msg = "电话为空";
			data.put("msg", msg);
			msg_en = "The phone number is empty";
			data.put("msg_en", msg_en);
			return data;
		}else{
			if(tel.length() > 15){
				msg = "电话长度超过15";
				data.put("msg", msg);
				msg_en = "Phone number Had Beyonded 15";
				data.put("msg_en", msg_en);
				return data;
			}
		}
		
		if("".equals(standby1)){
			msg = "公司为空";
			data.put("msg", msg);
			msg_en = "The company is empty";
			data.put("msg_en", msg_en);
			return data;
		}else{
			if(standby1.length() > 100){
				msg = "公司长度超过100";
				data.put("msg", msg);
				msg_en = "Company Had Beyonded 100";
				data.put("msg_en", msg_en);
				return data;
			}
		}
		TPermissionsStudentinfo tPermissionsStudentinfo = new TPermissionsStudentinfo();
		tPermissionsStudentinfo.setName(name);
		tPermissionsStudentinfo.setLogincode(logincode);
		tPermissionsStudentinfo.setNickname(nickname);
		tPermissionsStudentinfo.setPassword(password);
		//状态 激活
		tPermissionsStudentinfo.setStatus("0");
		//角色 学生
		tPermissionsStudentinfo.setAuthId("3");
		//部门 中新软件
		tPermissionsStudentinfo.setDepartid("xxx");
		tPermissionsStudentinfo.setTel(tel);
		tPermissionsStudentinfo.setStandby1(standby1);
		try{
			savePermissionsStudentinfo(tPermissionsStudentinfo);
			msg = "注册成功";
			data.put("flag", "1");
			data.put("msg", msg);
			msg_en = "Register Successful";
			data.put("msg_en", msg_en);
			return data;
		}catch(Exception e){
			e.printStackTrace();
			msg = "注册失败";
			data.put("msg", msg);
			msg_en = "Register Failure";
			data.put("msg_en", msg_en);
			return data;
		}
	}
}
