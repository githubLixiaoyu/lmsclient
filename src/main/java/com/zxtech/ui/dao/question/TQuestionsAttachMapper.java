package com.zxtech.ui.dao.question;

import java.util.List;

import com.zxtech.ui.vo.question.TQuestionsAttach;

public interface TQuestionsAttachMapper {
    int deleteByPrimaryKey(String attachid);

    int insert(TQuestionsAttach record);

    int insertSelective(TQuestionsAttach record);

    TQuestionsAttach selectByPrimaryKey(String attachid);

    int updateByPrimaryKeySelective(TQuestionsAttach record);

    int updateByPrimaryKeyWithBLOBs(TQuestionsAttach record);

    int updateByPrimaryKey(TQuestionsAttach record);
    
    int deleteQuestionsAttach(List<String> ids);
}