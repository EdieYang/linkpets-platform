package com.linkpets.core.dao;

import com.linkpets.core.model.CmsQuestionnaireItem;

import java.util.List;

public interface CmsQuestionnaireItemMapper {
    int deleteByPrimaryKey(String questionnaireItemId);

    int insert(CmsQuestionnaireItem record);

    int insertSelective(CmsQuestionnaireItem record);

    CmsQuestionnaireItem selectByPrimaryKey(String questionnaireItemId);

    int updateByPrimaryKeySelective(CmsQuestionnaireItem record);

    int updateByPrimaryKey(CmsQuestionnaireItem record);

    List<CmsQuestionnaireItem> getListByQuestionnaireId(String questionnaireId);

    void delQuestionnaireItemByQuestionnaireId(String questionnaireId);
}