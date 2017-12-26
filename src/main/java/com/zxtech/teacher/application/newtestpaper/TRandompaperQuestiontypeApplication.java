package com.zxtech.teacher.application.newtestpaper;

import java.util.List;
import java.util.Map;

import com.zxtech.ui.vo.newtestpaper.TRandompaperQuestiontype;

public interface TRandompaperQuestiontypeApplication {
	
	public List<Map<String, Object>> selectRandompaperQuestiontype(TRandompaperQuestiontype record) throws Exception;
}
