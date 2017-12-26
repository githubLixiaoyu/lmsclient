package com.zxtech.ui.application.exam;

import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewExamInfo;

public interface TNewexamInfoApplication {
	
	Map<String, Object> selectExamInfoByFilter(TNewExamInfo tNewExamInfo) throws Exception;
	
	Map<String, Object> selectPcExamInfoByFilter(TNewExamInfo tNewExamInfo) throws Exception;
	
	Map<String, Object> getUserExamInfo(TNewExamInfo tNewExamInfo) throws Exception;
}
