package com.zxtech.teacher.application.vrnewexam.impl;

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
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.esp.dao.vrnewexam.TestInstanceRequestMapper;
import com.zxtech.esp.dao.vrnewtestpaper.TestPaperOperationMapper;
import com.zxtech.esp.dao.vrnewtestpaper.TestTemplateMilestoneMapper;
import com.zxtech.esp.vo.vrnewexam.TestInstanceRequest;
import com.zxtech.esp.vo.vrnewtestpaper.TestPaperOperation;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplateMilestone;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.support.util.Util;
import com.zxtech.teacher.application.vrnewexam.TestInstanceRequestApplication;
import com.zxtech.ui.dao.permissions.TPermissionsStudentinfoMapper;
import com.zxtech.ui.vo.permissions.TPermissionsStudentinfo;

@Named
public class TestInstanceRequestApplicationImpl implements TestInstanceRequestApplication{
	@Inject
	private TestInstanceRequestMapper testInstanceRequestMapper;
	@Inject
	private TestTemplateMilestoneMapper testTemplateMilestoneMapper;
	@Inject
	private TestPaperOperationMapper testPaperOperationMapper;
	@Inject
	private TPermissionsStudentinfoMapper tPermissionsStudentinfoMapper;

	@Override
	public PageInfo<Map<String, Object>> selectTestInstanceRequestList(TestInstanceRequest record, int pageNum,
			int pageSize) throws Exception{
		Map<String, Object> userMap = selectAllUserMap(null);
		PageHelper.startPage(pageNum, pageSize);
//		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		List<Map<String, Object>> list = testInstanceRequestMapper.selectTestInstanceRequestList(record);
		for(int i=0;i<list.size();i++){
			Map<String, Object> m = list.get(i);
			String userId = m.get("requestUserId") == null ? "" : m.get("requestUserId").toString();
			Map<String, Object> userInfoMap = (Map<String, Object>) userMap.get(userId);
			if(userInfoMap != null){
				m.put("etpUserName", userInfoMap.get("logincode"));
			}
		}
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveTestInstanceRequest(TestInstanceRequest record) throws Exception{
		try{
			record.setRequestUserId(AuthDetailUtil.getLoginUserId());
			boolean updateFlag = true;
			long id = record.getId();
			if(id == 0){
				updateFlag = false;
			}
			//添加封图
			if(record.getImageName().indexOf("temp") > -1) {
				//获取临时图片路径
				String tempPath = Util.readPropertiesValue("TempImagesPath");
				//web端图片存储路径
				String examImagesPath = Util.readPropertiesValue("ExamImagesPath");
				
				File examImagesFile = new File(examImagesPath);
				if (!examImagesFile.exists()) {
					examImagesFile.mkdirs();
				}
				String imagename = record.getImageName().replace("temp_", "");
				File srcFile = new File(tempPath+"/"+record.getImageName());
				File imageFile = new File(examImagesPath+"/"+imagename);
				if(srcFile.exists()){
					//移动图片
					FileUtils.copyFile(srcFile, imageFile);
					//删除临时图片
					srcFile.delete();
				}
				record.setImageName(imagename);
			}
			record.setId(id);
			if(updateFlag){
				testInstanceRequestMapper.updateByPrimaryKeySelective(record);
				TestTemplateMilestone testTemplateMilestone = new TestTemplateMilestone();
				testTemplateMilestone.setMileStoneName(record.getMileStoneName());
				testTemplateMilestone.setId(record.getTestTemplateMileStoneId());
				testTemplateMilestoneMapper.updateByPrimaryKeySelective(testTemplateMilestone);
			}else{
				long testTemplateMileStoneId = record.getTestTemplateMileStoneId();
				//查询试卷信息
				Map<String, Object> testTemplateMilestoneMap = testTemplateMilestoneMapper.selectByPrimaryKey(testTemplateMileStoneId);
				testTemplateMilestoneMap.put("typeId", "4");
				testTemplateMilestoneMap.put("statusTypeId", "0");
				testTemplateMilestoneMap.put("createDate", new Date());
				testTemplateMilestoneMap.put("requestUserId", AuthDetailUtil.getLoginUserId());
				testTemplateMilestoneMap.put("mileStoneName", record.getMileStoneName());
				//插入考试信息 TestTemplateMilestone
				testTemplateMilestoneMapper.insertTestTemplateMilestone(testTemplateMilestoneMap);
				String milestoneId = testTemplateMilestoneMap.get("milestoneId").toString();
				long testTemplateId = Long.parseLong(testTemplateMilestoneMap.get("testTemplateId").toString());
				record.setTestTemplateMileStoneId(Long.parseLong(milestoneId));
				record.setTestTemplateId(testTemplateId);
				TestPaperOperation testPaperOperation = new TestPaperOperation();
				testPaperOperation.setMilestoneId(testTemplateMileStoneId);
				//查询试卷教学点
				List<Map<String, Object>> paperOptionList = testPaperOperationMapper.selectTestPaperOperationList(testPaperOperation);
				Map<String, Object> paperOptionMap = null;
				long paperScore = 0L;
				for(int i=0;i<paperOptionList.size();i++){
					paperOptionMap = paperOptionList.get(i);
					paperOptionMap.put("milestoneId", milestoneId);
					paperScore += Long.parseLong(paperOptionMap.get("score").toString());
					//插入考试教学点
					testPaperOperationMapper.insertTestPaperOperation(paperOptionMap);
				}
				record.setCreateDate(new Date());
				record.setVersion((long)0);
				record.setIsEnd((long)0);
				record.setPaperScore(paperScore);
				//插入考试信息
				testInstanceRequestMapper.insertSelective(record);
			}
			
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteTestInstanceRequest(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
//			tNewexamAuthoruserMapper.deleteNewexamAuthoruser(ids);
//			tNewexamImageMapper.deleteNewexamImage(ids);
			testInstanceRequestMapper.deleteTestInstanceRequest(ids);
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}

	@Override
	public Map<String, Object> selectTestInstanceRequestById(String id) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		TestInstanceRequest record = new TestInstanceRequest();
//		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		Map<String, Object> data = new HashMap<String, Object>();
		if(id != null && !"".equals(id)){
			record.setId(Long.parseLong(id));
			List<Map<String, Object>> list = testInstanceRequestMapper.selectTestInstanceRequestList(record);
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
				if(examinfo.get("imageName") != null && !"".equals(examinfo.get("imageName"))){
					examinfo.put("imagepath", basePath + imageDirpath + examImagesPath + "/" + examinfo.get("imageName"));
				}else{
					examinfo.put("imagepath", defaultPath);
				}
				data.put("examinfo", examinfo);
			}
		}
		return data;
	}

	@Override
	public int sendTestInstanceRequest(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return testInstanceRequestMapper.sendTestInstanceRequest(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"发布失败!");
		}
	}
	
	@Override
	public Map<String, Object> selectAllUserMap(TPermissionsStudentinfo record) throws Exception{
		List<Map<String, Object>> list = tPermissionsStudentinfoMapper.selectTPermissionsStudentinfoAllList(record);
		Map<String, Object> userMap = new HashMap<String, Object>();
		String[] userIdList = new String[list.size()];
		String userIdStr = "";
		for(int i=0;i<list.size();i++){
			Map<String, Object> m = list.get(i);
			String userId = m.get("studentid").toString();
			userIdList[i] = userId;
			userIdStr += userId+",";
			userMap.put(userId, m);
		}
		if(record != null && userIdList.length != 0 && (!"".equals(record.getLogincode()) || !"".equals(record.getNickname()) || !"".equals(record.getDepartid()))){
			userMap.put("userIdList", userIdList);
			userMap.put("userIdStr", userIdStr);
		}
		return userMap;
	}

	@Override
	public List<Map<String, Object>> selectOptionStatusTypeList() throws Exception {
		List<Map<String, Object>> list = testInstanceRequestMapper.selectTemplMilestoneStatusTypeList();
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String id = String.valueOf(map.get("id"));
			String text = String.valueOf(map.get("statusType"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", id);
			m.put("text", text);
			returnList.add(m);
		}
		return returnList;
	}
		
}
