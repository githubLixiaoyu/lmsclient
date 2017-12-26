package com.zxtech.manager.application.permissions.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxtech.manager.application.permissions.TPermissionsStudentDepartApplication;
import com.zxtech.support.exception.BusinessException;
import com.zxtech.ui.dao.permissions.TPermissionsStudentDepartMapper;
import com.zxtech.ui.vo.permissions.TPermissionsStudentDepart;

@Named
public class TPermissionsStudentDepartApplicationImpl implements TPermissionsStudentDepartApplication {
	@Inject
	private TPermissionsStudentDepartMapper tPermissionsStudentDepartMapper;

	@Override
	public PageInfo<Map<String, Object>> selectPermissionsStudentDepartList(TPermissionsStudentDepart record, int pageNum,
			int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) tPermissionsStudentDepartMapper.selectTPermissionsStudentDepartList(record);
		// 封装分页相关信息
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>();
		page.setTotal(result.getTotal());
		page.setList(result.getResult());
		return page;
	}

	@Override
	public List<Map<String, Object>> selectAllPermissionsStudentDepartList(TPermissionsStudentDepart record) throws Exception{
		List<Map<String, Object>> list = tPermissionsStudentDepartMapper.selectTPermissionsStudentDepartList(record);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String departcode = String.valueOf(map.get("departid"));
			String departname = String.valueOf(map.get("departname"));
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", departcode);
			m.put("text", departname);
			returnList.add(m);
		}
		return returnList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int savePermissionsStudentDepart(TPermissionsStudentDepart record) throws Exception{
		try{
			if(record.getDepartid() != null && !"".equals(record.getDepartid())){
				return tPermissionsStudentDepartMapper.updateByPrimaryKeySelective(record);
			}else{
				UUID uuid = UUID.randomUUID();
				record.setDepartid(uuid.toString());
				record.setCreatetime(new Date());
				record.setStatus("0");
				record.setDepartname(record.getDepartname().trim());
				record.setDepartcode(record.getDepartid());
				record.setParentid("xxx");
				tPermissionsStudentDepartMapper.insertSelective(record);
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deletePermissionsStudentDepart(String[] idList) throws Exception{
		try {
			List<String> ids = Arrays.asList(idList);
			return tPermissionsStudentDepartMapper.deleteTPermissionsStudentDepart(ids);
		} catch (Exception e) {
			throw new BusinessException(e,"删除公司失败!");
		}
	}

	@Override
	public Map<String, Object> findDepartByName(String departName) throws Exception{
		TPermissionsStudentDepart record = new TPermissionsStudentDepart();
		record.setDepartname(departName);
		List<Map<String, Object>> list = tPermissionsStudentDepartMapper.selectTPermissionsStudentDepartList(record);
		if(list != null && list.size() >0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean valideDepartName(TPermissionsStudentDepart record) throws Exception{
		Map<String, Object> m = findDepartByName(record.getDepartname());
		if(m == null || (record.getDepartname().equals(m.get("departname")) && record.getDepartid().equals(m.get("departid")))){
			return true;
		}else{
			return false;
		}
	}

}
