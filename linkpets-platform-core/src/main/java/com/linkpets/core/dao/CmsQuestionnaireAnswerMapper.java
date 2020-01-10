package com.linkpets.core.dao;

import com.linkpets.core.model.CmsQuestionnaireAnswer;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;

import java.util.List;

public interface CmsQuestionnaireAnswerMapper {
    int deleteByPrimaryKey(String answerId);

    int insert(CmsQuestionnaireAnswer record);

    int insertSelective(CmsQuestionnaireAnswer record);

    CmsQuestionnaireAnswer selectByPrimaryKey(String answerId);

    int updateByPrimaryKeySelective(CmsQuestionnaireAnswer record);

    int updateByPrimaryKey(CmsQuestionnaireAnswer record);

    List<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerList(String questionnaireId, String activityId);

    List<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerListByParams(String questionnaireId, String activityId, String userId);

    RespQuestionnaireAnswerInfo getQuestionnaireAnswerInfo(String answerId);

    List<CmsQuestionnaireAnswer> getQuestionnaireAnswerListByUserId(String userId, String activityId, String questionnaireId);


}