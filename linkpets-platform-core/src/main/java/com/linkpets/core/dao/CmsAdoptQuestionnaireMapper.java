package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptQuestionnaire;

import java.util.List;

public interface CmsAdoptQuestionnaireMapper {
    int deleteByPrimaryKey(String questionnaireId);

    int insert(CmsAdoptQuestionnaire record);

    int insertSelective(CmsAdoptQuestionnaire record);

    CmsAdoptQuestionnaire selectByPrimaryKey(String questionnaireId);

    int updateByPrimaryKeySelective(CmsAdoptQuestionnaire record);

    int updateByPrimaryKey(CmsAdoptQuestionnaire record);

    List<CmsAdoptQuestionnaire> getQuestionnaireList(String questionnaireTitle);

    void delQuestionnaireByQuestionnaireId(String questionnaireId);
}