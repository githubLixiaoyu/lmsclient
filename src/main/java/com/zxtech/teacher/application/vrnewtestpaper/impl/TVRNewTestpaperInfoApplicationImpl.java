package com.zxtech.teacher.application.vrnewtestpaper.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.esp.dao.vrnewtestpaper.TestPaperOperationMapper;
import com.zxtech.esp.dao.vrnewtestpaper.TestTemplateMapper;
import com.zxtech.esp.dao.vrnewtestpaper.TestTemplateMilestoneMapper;
import com.zxtech.esp.dao.vrnewtestpaper.TestTemplateOperationMapper;
import com.zxtech.esp.vo.vrnewtestpaper.TestPaperOperation;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplate;
import com.zxtech.esp.vo.vrnewtestpaper.TestTemplateMilestone;
import com.zxtech.teacher.application.vrnewtestpaper.TVRNewTestpaperInfoApplication;
import com.zxtech.ui.dao.permissions.TPermissionsStudentinfoMapper;

@Named
public class TVRNewTestpaperInfoApplicationImpl implements TVRNewTestpaperInfoApplication {
	@Inject
	private TestTemplateMilestoneMapper testTemplateMilestoneMapper;
	@Inject
	private TestTemplateMapper testTemplateMapper;
	@Inject
	private TPermissionsStudentinfoMapper tPermissionsStudentinfoMapper;
	@Inject
	private TestTemplateOperationMapper testTemplateOperationMapper;
	@Inject
	private TestPaperOperationMapper testPaperOperationMapper;

	@Override
	public PageInfo<Map<String, Object>> selectTestTemplateMilestoneList(TestTemplateMilestone record, int pageNum,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) testTemplateMilestoneMapper
				.selectTestTemplateMilestoneList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveTestTemplateMilestone(TestTemplateMilestone record) throws Exception {
		if (record.getId() == null) {
			record.setVersion((long) 0);
			record.setCreateDate(new Date());
			record.setSequenceNumber((long) 15);
			record.setStatusTypeId((long) 0);
			record.setTypeId((long) 3);
			record.setMovieId((long) 0);
			record.setRequestUserId(AuthDetailUtil.getLoginUserId());
			record.setSimulatorType("");
			record.setUrlProtocol("");
			record.setPlateformFlag("etp");
			// 添加
			System.out.println(record);
			return testTemplateMilestoneMapper.insertSelective(record);
		} else {
			// 修改
			return testTemplateMilestoneMapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public List<Map<String, Object>> selectVRTestTemplateList(TestTemplate record) throws Exception {
		List<Map<String, Object>> list = testTemplateMapper.selectVRTestTemplateList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				String id = String.valueOf(map.get("id"));
				String text = String.valueOf(map.get("testName"));
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", id);
				m.put("text", text);
				returnList.add(m);
			}
		}
		return returnList;
	}

	@Override
	public List<Map<String, Object>> selectAllUserList() throws Exception {
		List<Map<String, Object>> result = tPermissionsStudentinfoMapper.selectAllUserList();

		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateTestStatusType(String[] idList, int statusTypeId) throws Exception {
		List<String> ids = Arrays.asList(idList);
		int num = 0;
		if (statusTypeId == 1) {
			num = testTemplateMilestoneMapper.updateTestStatusType(ids);
		}
		if (statusTypeId == 2) {
			num = testTemplateMilestoneMapper.deleteTestStatusType(ids);
		}
		return num;
	}

	@Override
	public List<Map<String, Object>> selectTestTemplateMilestoneListById(long paperId) throws Exception {
		List<Map<String, Object>> list = testTemplateMilestoneMapper.selectTestTemplateMilestoneListById(paperId);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectTestTemplateOperationByPaperId(long paperId) throws Exception {
		List<Map<String, Object>> list = testTemplateOperationMapper.selectTestTemplateOperationByPaperId(paperId);
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveTestPaperOperation(long paperId, long[] checkNameId, long[] checkSequence, long[] checkSorce)
			throws Exception {

		testPaperOperationMapper.deleteTestPaperOperationByMilestoneId(paperId);
		int num = 0;
		for (int i = 0; i < checkNameId.length; i++) {
			List<Map<String, Object>> list = testTemplateOperationMapper
					.selectTestTemplateOperationById(checkNameId[i]);
			TestPaperOperation tpo = new TestPaperOperation();
			tpo.setVersion((long) 0);
			tpo.setMilestoneId(paperId);
			tpo.setReferenceMilestoneId((Long) list.get(0).get("milestoneId"));
			tpo.setReferenceOperationId(checkNameId[i]);
			tpo.setScore(checkSorce[i]);
			tpo.setSequenceNumber(checkSequence[i]);
			num = num + testPaperOperationMapper.insertSelective(tpo);
		}

		return num;
	}

	@Override
	public List<Map<String, Object>> selectTPaperOperation(long mileStoneId) throws Exception {
		List<Map<String, Object>> list = testPaperOperationMapper.selectTestTemplateOperationBymileStoneId(mileStoneId);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectTestPaperInfo(long mileStoneId) throws Exception {
		return testPaperOperationMapper.selectTestPaperInfo(mileStoneId);
	}

}
