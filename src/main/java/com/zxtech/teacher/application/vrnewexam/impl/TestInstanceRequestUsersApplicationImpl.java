package com.zxtech.teacher.application.vrnewexam.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.esp.dao.vrnewexam.TestInstanceMapper;
import com.zxtech.esp.dao.vrnewexam.TestInstanceRequestUsersMapper;
import com.zxtech.esp.vo.vrnewexam.TestInstance;
import com.zxtech.esp.vo.vrnewexam.TestInstanceRequestUsers;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.teacher.application.vrnewexam.TestInstanceRequestApplication;
import com.zxtech.teacher.application.vrnewexam.TestInstanceRequestUsersApplication;
import com.zxtech.ui.dao.newexam.TNewexamAuthoruserMapper;
import com.zxtech.ui.vo.newexam.TNewexamAuthoruser;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@Named
public class TestInstanceRequestUsersApplicationImpl implements TestInstanceRequestUsersApplication{
	@Inject
	private TestInstanceRequestUsersMapper testInstanceRequestUsersMapper;
	@Inject
	private TNewexamAuthoruserMapper tNewexamAuthoruserMapper;
	@Inject
	private TestInstanceMapper testInstanceMapper;
	@Autowired
	private TestInstanceRequestApplication testInstanceRequestApplication;

	@Override
	public PageInfo<Map<String, Object>> selectTestInstanceRequestUsersList(TestInstanceRequestUsers record, int pageNum,
			int pageSize) throws Exception{
		List<Map<String, Object>> testInstanceRequestUsersList = testInstanceRequestUsersMapper.selectTestInstanceRequestUsersList(record);
		String[] userIdList = new String[testInstanceRequestUsersList.size()];
		for(int i=0;i<testInstanceRequestUsersList.size();i++){
			Map<String, Object> m = testInstanceRequestUsersList.get(i);
			String userId = m.get("userId").toString();
			userIdList[i] = userId;
		}
		PageHelper.startPage(pageNum, pageSize);
		TNewexamAuthoruser tNewexamAuthoruser = new TNewexamAuthoruser();
		if(userIdList.length > 0){
			tNewexamAuthoruser.setUserIdList(userIdList);
		}else{
			String[] userIdList1 = {""};
			tNewexamAuthoruser.setUserIdList(userIdList1);
		}
		tNewexamAuthoruser.setLoginUserId(AuthDetailUtil.getLoginUserId());
		tNewexamAuthoruser.setName(record.getName());
		tNewexamAuthoruser.setDepartid(record.getDepartid());
		tNewexamAuthoruser.setLogincode(record.getLogincode());
		tNewexamAuthoruser.setNickname(record.getNickname());
		
		List<Map<String, Object>> list = tNewexamAuthoruserMapper.selectUserForTestInstanceRequestList(tNewexamAuthoruser);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}
	
	@Override
	public PageInfo<Map<String, Object>> selectCanSelectForTestInstanceRequestList(TestInstanceRequestUsers record, int pageNum,
			int pageSize) throws Exception{
		List<Map<String, Object>> testInstanceRequestUsersList = testInstanceRequestUsersMapper.selectTestInstanceRequestUsersList(record);
		String[] userIdList = new String[testInstanceRequestUsersList.size()];
		for(int i=0;i<testInstanceRequestUsersList.size();i++){
			Map<String, Object> m = testInstanceRequestUsersList.get(i);
			String userId = m.get("userId").toString();
			userIdList[i] = userId;
		}
		PageHelper.startPage(pageNum, pageSize);
		TNewexamAuthoruser tNewexamAuthoruser = new TNewexamAuthoruser();
		if(userIdList.length > 0){
			tNewexamAuthoruser.setUserIdList(userIdList);
		}else{
			String[] userIdList1 = {""};
			tNewexamAuthoruser.setUserIdList(userIdList1);
		}
		tNewexamAuthoruser.setLoginUserId(AuthDetailUtil.getLoginUserId());
		tNewexamAuthoruser.setName(record.getName());
		tNewexamAuthoruser.setDepartid(record.getDepartid());
		tNewexamAuthoruser.setLogincode(record.getLogincode());
		tNewexamAuthoruser.setNickname(record.getNickname());
		List<Map<String, Object>> list = tNewexamAuthoruserMapper.selectCanSelectForTestInstanceRequestList(tNewexamAuthoruser);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveTestInstanceRequestUsers(String[] idList, String testInstanceRequestId) throws Exception{
		try{
			TestInstanceRequestUsers testInstanceRequestUsers = null;
			for(int i=0;i<idList.length;i++){
				testInstanceRequestUsers = new TestInstanceRequestUsers();
				testInstanceRequestUsers.setTestInstanceRequestId(Long.parseLong(testInstanceRequestId));
				testInstanceRequestUsers.setUserId(idList[i]);
				testInstanceRequestUsers.setVersion((long)0);
				testInstanceRequestUsers.setUserTestStateTypeId((long)0);
				testInstanceRequestUsersMapper.insertSelective(testInstanceRequestUsers);
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteTestInstanceRequestUsers(String[] idList, String testInstanceRequestId) throws Exception{
		try {
			TestInstanceRequestUsers testInstanceRequestUsers = null;
			for(int i=0;i<idList.length;i++){
				testInstanceRequestUsers = new TestInstanceRequestUsers();
				testInstanceRequestUsers.setTestInstanceRequestId(Long.parseLong(testInstanceRequestId));
				testInstanceRequestUsers.setUserId(idList[i]);
				testInstanceRequestUsersMapper.deleteTestInstanceRequestUsers(testInstanceRequestUsers);
			}
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}
	
	@Override
	public PageInfo<Map<String, Object>> selectExamResultList(TestInstanceRequestUsers record, int pageNum,
			int pageSize) throws Exception {
//		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		TPermissionsStudentinfo studentInfo = new TPermissionsStudentinfo();
		studentInfo.setDepartid(record.getDepartid());
		studentInfo.setLogincode(record.getLogincode());
		studentInfo.setNickname(record.getNickname());
		Map<String, Object> userMap = testInstanceRequestApplication.selectAllUserMap(studentInfo);
		String[] userIdList = (String[]) userMap.get("userIdList");
		String userIdStr = userMap.get("userIdStr") == null ? "" : userMap.get("userIdStr").toString();
		TestInstance testInstance = new TestInstance();
		testInstance.setTestInstanceRequestId(record.getTestInstanceRequestId());
		testInstance.setUserIdList(userIdList);
		testInstance.setUserIdStr(userIdStr);
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = testInstanceMapper.selectTestInstanceList(testInstance);
		for(int i=0;i<list.size();i++){
			Map<String, Object> m = list.get(i);
			String userId = m.get("userId") == null ? "" : m.get("userId").toString();
			Map<String, Object> userInfoMap = (Map<String, Object>) userMap.get(userId);
			m.put("logincode", userInfoMap.get("logincode"));
			m.put("nickname", userInfoMap.get("nickname"));
			m.put("departname", userInfoMap.get("departname"));
		}
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}
}
