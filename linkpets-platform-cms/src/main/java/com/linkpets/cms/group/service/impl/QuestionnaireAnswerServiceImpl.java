package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IQuestionnaireAnswerService;
import com.linkpets.core.dao.CmsQuestionnaireAnswerMapper;
import com.linkpets.core.model.CmsQuestionnaireAnswer;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class QuestionnaireAnswerServiceImpl implements IQuestionnaireAnswerService {
    @Resource
    private CmsQuestionnaireAnswerMapper questionnaireAnswerMapper;

    @Override
    public List<CmsQuestionnaireAnswer> getQuestionnaireAnswerListByUserId(String userId, String activityId, String questionnaireId) {
        return questionnaireAnswerMapper.getQuestionnaireAnswerListByUserId(userId, activityId, questionnaireId);
    }


    @Override
    public String crtAnswer(CmsQuestionnaireAnswer cmsQuestionnaireAnswer) {
        String answerId = UUIDUtils.getId();
        cmsQuestionnaireAnswer.setAnswerId(answerId);
        cmsQuestionnaireAnswer.setCreateDate(new Date());
        questionnaireAnswerMapper.insertSelective(cmsQuestionnaireAnswer);
        return answerId;
    }


    @Override
    public PageInfo<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerPage(String questionnaireId, String activityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespQuestionnaireAnswerInfo> answerInfoList = questionnaireAnswerMapper.getQuestionnaireAnswerList(questionnaireId, activityId);
        return new PageInfo<>(answerInfoList);
    }

    @Override
    public List<RespQuestionnaireAnswerInfo> getQuestionnaireAnswerList(String questionnaireId, String activityId, String userId) {
        return questionnaireAnswerMapper.getQuestionnaireAnswerListByParams(questionnaireId, activityId, userId);
    }

    @Override
    public RespQuestionnaireAnswerInfo getQuestionnaireAnswerInfo(String answerId, String userId, String activityId) {
        if (StringUtils.isNotEmpty(answerId)) {
            return questionnaireAnswerMapper.getQuestionnaireAnswerInfo(answerId);
        } else if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(activityId)) {
            return questionnaireAnswerMapper.getQuestionnaireAnswerInfoByUserIdAndActivityId(userId, activityId);
        }
        return null;
    }

}
