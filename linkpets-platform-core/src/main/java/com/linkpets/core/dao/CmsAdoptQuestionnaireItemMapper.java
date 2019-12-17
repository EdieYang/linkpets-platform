package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptQuestionnaireItem;

import java.util.List;

public interface CmsAdoptQuestionnaireItemMapper {
    int deleteByPrimaryKey(String questionnaireItemId);

    int insert(CmsAdoptQuestionnaireItem record);

    int insertSelective(CmsAdoptQuestionnaireItem record);

    CmsAdoptQuestionnaireItem selectByPrimaryKey(String questionnaireItemId);

    int updateByPrimaryKeySelective(CmsAdoptQuestionnaireItem record);

    int updateByPrimaryKey(CmsAdoptQuestionnaireItem record);

    List<CmsAdoptQuestionnaireItem> getListByQuestionnaireId(String questionnaireId);

    void delQuestionnaireItemByQuestionnaireId(String questionnaireId);
}