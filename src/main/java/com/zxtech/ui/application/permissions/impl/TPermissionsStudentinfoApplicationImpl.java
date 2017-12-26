package com.zxtech.ui.application.permissions.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.zxtech.auth.CustomUserDetails;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.support.md5.MessageDigestUtil;
import com.zxtech.ui.application.permissions.TPermissionsStudentinfoApplication;
import com.zxtech.ui.dao.permissions.TPermissionsStudentinfoMapper;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@Named
public class TPermissionsStudentinfoApplicationImpl implements TPermissionsStudentinfoApplication{
	@Inject
	private TPermissionsStudentinfoMapper tPermissionsStudentinfoMapper;
	@Override
	public int deleteByPrimaryKey(String studentid) throws Exception{
		return tPermissionsStudentinfoMapper.deleteByPrimaryKey(studentid);
	}
	@Override
	public int insert(TPermissionsStudentinfo record) throws Exception{
		return tPermissionsStudentinfoMapper.insert(record);
	}
	@Override
	public int insertSelective(TPermissionsStudentinfo record) throws Exception{
		return tPermissionsStudentinfoMapper.insertSelective(record);
	}
	@Override
	public Map<String, Object> selectByPrimaryKey(String studentid) throws Exception{
		return tPermissionsStudentinfoMapper.selectByPrimaryKey(studentid);
	}
	@Override
	public int updateByPrimaryKeySelective(TPermissionsStudentinfo record) throws Exception{
		return tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKeyWithBLOBs(TPermissionsStudentinfo record) throws Exception{
		return tPermissionsStudentinfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}
	@Override
	public int updateByPrimaryKey(TPermissionsStudentinfo record) throws Exception{
		return tPermissionsStudentinfoMapper.updateByPrimaryKey(record);
	}
	@Override
	public Map<String, Object> selectLoginUser() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String nickName = AuthDetailUtil.getNickName();
		map.put("nickName", AuthDetailUtil.getNickName());
		if (!"".equals(nickName)) {
			map.put("flag", 1);
		} else {
			map.put("flag", 0);
		}
		return map;
	}
	@Override
	public Map<String, Object> getUserInfo() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		CustomUserDetails userDetails = AuthDetailUtil.getLoginInfo();
		if (userDetails != null) {
			String photoName = userDetails.getPhotoName();
			String nickName = userDetails.getNickName();
			String userId = userDetails.getUserId();
			
			map.put("userId", userId);
			map.put("photoName", photoName);
			map.put("nickName", nickName);
		}
		return map;
	}
	@Override
	public int updateNickName(TPermissionsStudentinfo record) throws Exception{
		String studentid = AuthDetailUtil.getLoginInfo().getUserId();
		record.setStudentid(studentid);
		int flag = tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(record);
		if (flag == 1) {
			AuthDetailUtil.resetNickName(record.getNickname());
		}
		return flag;
	}
	@Override
	public int updatePassword(TPermissionsStudentinfo record) throws Exception{
		String studentid = AuthDetailUtil.getLoginInfo().getUserId();
		record.setStudentid(studentid);
		String pwd = record.getPassword();
		pwd = MessageDigestUtil.encode(pwd);
		record.setPassword(pwd);
		int flag = tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(record);
		if (flag == 1) {
			AuthDetailUtil.resetPassword(pwd);
		}
		return flag;
	}
	@Override
	public int updatePhotoName(String photoName) throws Exception{
		String studentid = AuthDetailUtil.getLoginInfo().getUserId();
		TPermissionsStudentinfo record = new TPermissionsStudentinfo();
		record.setStudentid(studentid);
		record.setPhotoname(photoName);
		int flag = tPermissionsStudentinfoMapper.updateByPrimaryKeySelective(record);
		if (flag == 1) {
			AuthDetailUtil.resetPhotoName(photoName);
		}
		return flag;
	}
}
