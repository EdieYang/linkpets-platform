package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptQuestionnaireAnswer;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;

import java.util.List;

public interface CmsAdoptQuestionnaireAnswerMapper {
    int deleteByPrimaryKey(String answerId);

    int insert(CmsAdoptQuestionnaireAnswer record);

    int insertSelective(CmsAdoptQuestionnaireAnswer record);

    CmsAdoptQuestionnaireAnswer selectByPrimaryKey(String answerId);

    int updateByPrimaryKeySelective(CmsAdoptQuestionnaireAnswer record);

    int updateByPrimaryKey(CmsAdoptQuestionnaireAnswer record);

    List<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerList(String questionnaireId, String activityId);

    RespQuestionnaireAnswerInfo getQuestionnaireAnswerInfo(String answerId);
}