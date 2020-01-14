package com.linkpets.cms.group.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsQuestionnaireAnswer;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;

import java.util.List;

public interface IQuestionnaireAnswerService {

    List<CmsQuestionnaireAnswer> getQuestionnaireAnswerListByUserId(String userId, String activityId, String questionnaireId);

    /**
     * 创建答案
     *
     * @param cmsQuestionnaireAnswer
     * @return
     */
    String crtAnswer(CmsQuestionnaireAnswer cmsQuestionnaireAnswer);

    /**
     * 分页查询问卷答案列表
     *
     * @param questionnaireId
     * @param activityId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerPage(String questionnaireId, String activityId, Integer pageNum, Integer pageSize);


    /**
     * 查询问卷答案列表
     *
     * @param questionnaireId
     * @param activityId
     * @param userId
     * @return
     */
    List<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerList(String questionnaireId, String activityId, String userId);

    /**
     * 查询问卷答案详情
     *
     * @param answerId
     * @param userId
     * @param activityId
     * @return
     */
    RespQuestionnaireAnswerInfo getQuestionnaireAnswerInfo(String answerId, String userId, String activityId);
}
