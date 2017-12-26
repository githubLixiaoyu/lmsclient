package com.zxtech.common.application.function;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewExamInfo;
import com.zxtech.ui.vo.newtestpaper.TNewTestpaperInfo;

public interface CommonApplication {
	
	public List<Map<String, Object>> getFunctionMenu() throws Exception;
	
	public List<Map<String, Object>> getPaperInfoById(TNewTestpaperInfo tNewTestpaperInfo) throws Exception;
	
	public List<Map<String, Object>> selectPaperOptionsByFilter(TNewExamInfo tNewExamInfo) throws Exception;
	
	public Map<String, Object> selectPaperAnswersByFilter(TNewExamInfo tNewExamInfo) throws Exception;

	public List<Map<String, Object>> selectVrAnswersByFilter(TNewExamInfo tNewExamInfo) throws Exception;
}
