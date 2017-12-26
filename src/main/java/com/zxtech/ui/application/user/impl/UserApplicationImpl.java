package com.zxtech.ui.application.user.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.ui.application.user.UserApplication;
import com.zxtech.ui.dao.user.UserMapper;
import com.zxtech.ui.vo.user.UserVo;

@Named
public class UserApplicationImpl implements UserApplication {
	@Inject
	private UserMapper userMapper;

	/**
	 * 修改用户密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	public int updateByPassword(UserVo record) throws Exception {
		if(StringUtils.isEmpty(record.getUserId())){
			record.setUserId(AuthDetailUtil.getLoginUserId());
		}
//		record.setPassword(MessageDigestUtil.encode(record.getPassword(), "MD5"));
		
		int result = userMapper.updateByPassword(record);
		//更新凭证
		AuthDetailUtil.resetPassword(record.getPassword());
		return result;
	}
	
	
	/**
	 * 重置密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	public int resetPassword(UserVo record) throws Exception {
		if(StringUtils.isEmpty(record.getPassword())){
			record.setPassword("Tce88888");//重置密码
		}
		
//		record.setPassword(MessageDigestUtil.encode(record.getPassword(), "MD5"));
		
		int result = userMapper.updateByPassword(record);
		
		return result;
	}

	/**
	 * 查询用户全部列表
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectByAll() throws Exception {
		return userMapper.selectByAll();
	}

	/**
	 * 根据用户Id查详细
	 * 
	 * @param UserId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectByPrimaryKey(String userId) throws Exception {
		return userMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 查询、搜索、分页
	 * 
	 * @author dinchao
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @return selectByKey
	 * @throws Exception
	 */
	@Override
	public PageInfo<Map<String, Object>> selectByKey(UserVo record, int pageNum, int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		record.setOrgNameId(record.getOrgPartiName());
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) userMapper.selectByKey(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	public int deleteByPrimaryKey(String userId) throws Exception {

		return userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(UserVo record) throws Exception {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(UserVo record) throws Exception {
		return userMapper.insertSelective(record);
	}
	/**
	 * 查询批准用户
	 */
	@Override
	public List<Map<String, Object>> selectAllUser(String organizationId,String upperOrganizationId) throws Exception {
		return userMapper.selectAllUser(organizationId,upperOrganizationId);
	}
}
