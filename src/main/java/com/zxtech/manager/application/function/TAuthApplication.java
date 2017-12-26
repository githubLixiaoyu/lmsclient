package com.zxtech.manager.application.function;

import java.util.List;
import java.util.Map;

import com.zxtech.manager.vo.function.TAuth;

public interface TAuthApplication {
	
	//下拉框
	public List<Map<String, Object>> selectOptionAuthList(TAuth record) throws Exception;
}
