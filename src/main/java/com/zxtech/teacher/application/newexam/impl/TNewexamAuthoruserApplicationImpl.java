package com.zxtech.teacher.application.newexam.impl;

import java.util.Date;
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
import com.zxtech.support.exception.BusinessException;
import com.zxtech.teacher.application.newexam.TNewexamAuthoruserApplication;
import com.zxtech.ui.dao.newexam.TNewexamAuthoruserMapper;
import com.zxtech.ui.vo.newexam.TNewexamAuthoruser;

@Named
public class TNewexamAuthoruserApplicationImpl implements TNewexamAuthoruserApplication{
	@Inject
	private TNewexamAuthoruserMapper tNewexamAuthoruserMapper;

	@Override
	public PageInfo<Map<String, Object>> selectNewexamAuthoruserList(TNewexamAuthoruser record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		List<Map<String, Object>> list = tNewexamAuthoruserMapper.selectNewexamAuthoruserList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}
	
	@Override
	public PageInfo<Map<String, Object>> selectCanSelectAuthoruserList(TNewexamAuthoruser record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		List<Map<String, Object>> list = tNewexamAuthoruserMapper.selectCanSelectAuthoruserList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveNewexamAuthoruser(String[] idList, String examid) throws Exception{
		try{
			TNewexamAuthoruser tNewexamAuthoruser = null;
			for(int i=0;i<idList.length;i++){
				tNewexamAuthoruser = new TNewexamAuthoruser();
				tNewexamAuthoruser.setExamid(examid);
				tNewexamAuthoruser.setUserid(idList[i]);
				tNewexamAuthoruser.setAuthortime(new Date());
				tNewexamAuthoruserMapper.insertSelective(tNewexamAuthoruser);
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteNewexamAuthoruser(String[] idList, String examid) throws Exception{
		try {
			TNewexamAuthoruser tNewexamAuthoruser = null;
			for(int i=0;i<idList.length;i++){
				tNewexamAuthoruser = new TNewexamAuthoruser();
				tNewexamAuthoruser.setExamid(examid);
				tNewexamAuthoruser.setUserid(idList[i]);
				tNewexamAuthoruserMapper.deleteByPrimaryKey(tNewexamAuthoruser);
			}
			return 1;
		} catch (Exception e) {
			throw new BusinessException(e,"删除失败!");
		}
	}
	
	@Override
	public PageInfo<Map<String, Object>> selectExamResultList(TNewexamAuthoruser record, int pageNum,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		record.setLoginUserId(AuthDetailUtil.getLoginUserId());
		List<Map<String, Object>> list = tNewexamAuthoruserMapper.selectExamResultList(record);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) list;
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}
}
