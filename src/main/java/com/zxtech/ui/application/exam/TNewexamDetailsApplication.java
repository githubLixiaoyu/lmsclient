package com.zxtech.ui.application.exam;

import java.util.Map;

import com.zxtech.ui.vo.newexam.TNewexamDetails;

public interface TNewexamDetailsApplication {
	
	int saveExamDetails(TNewexamDetails tNewexamDetails) throws Exception;
	
	Map<String, Object> saveUserExam(TNewexamDetails tNewexamDetails) throws Exception;
}
