package com.linkpets.core.dao;

import com.linkpets.core.model.CmsQuestionnaire;

import java.util.List;

public interface CmsAdoptQuestionnaireMapper {
    int deleteByPrimaryKey(String questionnaireId);

    int insert(CmsQuestionnaire record);

    int insertSelective(CmsQuestionnaire record);

    CmsQuestionnaire selectByPrimaryKey(String questionnaireId);

    int updateByPrimaryKeySelective(CmsQuestionnaire record);

    int updateByPrimaryKey(CmsQuestionnaire record);

    List<CmsQuestionnaire> getQuestionnaireList(String questionnaireTitle);

    void delQuestionnaireByQuestionnaireId(String questionnaireId);
}