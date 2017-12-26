package com.zxtech.ui.application.user;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zxtech.ui.vo.user.UserVo;

public interface UserApplication {

	/**
	 * 修改用户密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	int updateByPassword(UserVo record) throws Exception;
	
	/**
	 * 重置密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	int resetPassword(UserVo record) throws Exception;

	/**
	 * 查询用户全部列表
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectByAll() throws Exception;

	/**
	 * 根据用户Id查详细
	 * 
	 * @param UserId
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectByPrimaryKey(String userId) throws Exception;

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
	PageInfo<Map<String, Object>> selectByKey(UserVo record, int pageNum, int pageSize) throws Exception;

	int deleteByPrimaryKey(String userId) throws Exception;

	int insert(UserVo record) throws Exception;

	int insertSelective(UserVo record) throws Exception;
	/**
	 * 查询批准用户
	 * @param organizationId
	 * @param upperOrganizationId
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectAllUser(String organizationId,String upperOrganizationId)throws Exception;

}
