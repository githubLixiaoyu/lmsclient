package com.zxtech.ui.application.exam;

import com.zxtech.ui.vo.newexam.TNewexamProcedure;

public interface TNewexamProcedureApplication {
	
	int saveExamProblemState(TNewexamProcedure tNewexamProcedure) throws Exception;
	
	int saveExamQuestionAnswer(TNewexamProcedure tNewexamProcedure) throws Exception;
}
