package com.zxtech.ui.dao.user;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.user.UserVo;

public interface UserMapper {

	/**
	 * 修改用户密码
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	int updateByPassword(UserVo record) throws Exception;

	/**
	 * 查询用户全部列表
	 * 
	 * @author dingchao
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectByAll();

	/**
	 * 根据用户Id查详细
	 * 
	 * @author dingchao
	 * @param UserId
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectByPrimaryKey(String userId);

	/**
	 * 保存
	 * 
	 * @author dingchao
	 * @param record
	 * @return
	 * @throws Exception
	 */
	int updateByPrimaryKeySelective(UserVo record);

	/**
	 * 根据用户Id删除记录（实为更新）
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	int updateByPrimaryKey(String userId);

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
	List<Map<String, Object>> selectByKey(UserVo record);

	int deleteByPrimaryKey(String userId);

	int insert(UserVo record);

	int insertSelective(UserVo record);
	/**
	 * 查询批准用户
	 * @param organizationId
	 * @param upperOrganizationId
	 * @return
	 */
	List<Map<String, Object>> selectAllUser(String organizationId,String upperOrganizationId);

}