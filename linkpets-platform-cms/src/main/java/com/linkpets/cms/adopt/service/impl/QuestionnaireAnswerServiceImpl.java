package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IQuestionnaireAnswerService;
import com.linkpets.core.dao.CmsAdoptQuestionnaireAnswerMapper;
import com.linkpets.core.model.CmsAdoptQuestionnaireAnswer;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class QuestionnaireAnswerServiceImpl implements IQuestionnaireAnswerService {
    @Resource
    private CmsAdoptQuestionnaireAnswerMapper questionnaireAnswerMapper;

    @Override
    public List<CmsAdoptQuestionnaireAnswer> getQuestionnaireAnswerListByUserId(String userId, String activityId, String questionnaireId) {
        return questionnaireAnswerMapper.getQuestionnaireAnswerListByUserId(userId, activityId, questionnaireId);
    }


    @Override
    public String crtAnswer(CmsAdoptQuestionnaireAnswer cmsAdoptQuestionnaireAnswer) {
        String answerId = UUIDUtils.getId();
        cmsAdoptQuestionnaireAnswer.setAnswerId(answerId);
        cmsAdoptQuestionnaireAnswer.setCreateDate(new Date());
        questionnaireAnswerMapper.insertSelective(cmsAdoptQuestionnaireAnswer);
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
    public RespQuestionnaireAnswerInfo getQuestionnaireAnswerInfo(String answerId) {
        return questionnaireAnswerMapper.getQuestionnaireAnswerInfo(answerId);
    }

}
