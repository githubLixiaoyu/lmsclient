package com.zxtech.manager.application.permissions.impl;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.auth.util.AuthDetailUtil;
import com.zxtech.manager.application.permissions.TLoginInfoApplication;
import com.zxtech.ui.dao.permissions.TLoginInfoMapper;
import com.zxtech.ui.vo.permissions.TLoginInfo;

@Named
public class TLoginIofoApplicationImpl implements TLoginInfoApplication{
	@Inject
	private TLoginInfoMapper tLoginInfoMapper;

	@Override
	public PageInfo<Map<String, Object>> selectLoginInfoList(TLoginInfo record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		String endDate = record.getEndDate();
		if(!"".equals(endDate)){
			endDate = endDate + " 23:59:59";
			record.setEndDate(endDate);
		}
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tLoginInfoMapper.selectLoginIfoList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveLoginInfo() throws Exception{
		try{
			String studentId = AuthDetailUtil.getLoginUserId();
			TLoginInfo record = new TLoginInfo();
			record.setStudentId(studentId);
			record.setCreateDate(new Date());
			tLoginInfoMapper.insertSelective(record);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
