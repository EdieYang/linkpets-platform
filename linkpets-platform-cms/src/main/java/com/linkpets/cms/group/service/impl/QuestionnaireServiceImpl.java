package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IQuestionnaireService;
import com.linkpets.core.dao.CmsQuestionnaireItemMapper;
import com.linkpets.core.dao.CmsQuestionnaireMapper;
import com.linkpets.core.model.CmsQuestionnaire;
import com.linkpets.core.model.CmsQuestionnaireItem;
import com.linkpets.core.respEntity.RespQuestionnaireInfo;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService {


    @Resource
    private CmsQuestionnaireMapper questionnaireMapper;

    @Resource
    private CmsQuestionnaireItemMapper questionnaireItemMapper;

    @Override
    public PageInfo<CmsQuestionnaire> getQuestionnairePage(String questionnaireTitle, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsQuestionnaire> questionnaireList = questionnaireMapper.getQuestionnaireList(questionnaireTitle);
        return new PageInfo<>(questionnaireList);
    }

    @Override
    public List<CmsQuestionnaire> getQuestionnaireList(String questionnaireTitle) {
        return questionnaireMapper.getQuestionnaireList(questionnaireTitle);
    }

    @Override
    public RespQuestionnaireInfo getQuestionnaireInfo(String questionnaireId) {
        RespQuestionnaireInfo respQuestionnaireInfo = new RespQuestionnaireInfo();
        CmsQuestionnaire questionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        List<CmsQuestionnaireItem> questionnaireItemList = questionnaireItemMapper.getListByQuestionnaireId(questionnaireId);
        respQuestionnaireInfo.setQuestionnaire(questionnaire);
        respQuestionnaireInfo.setQuestionnaireItemList(questionnaireItemList);
        return respQuestionnaireInfo;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtQuestionnaire(CmsQuestionnaire Questionnaire, List<CmsQuestionnaireItem> cmsQuestionnaireItemList) {
        String questionnaireId = UUIDUtils.getId();
        Questionnaire.setQuestionnaireId(questionnaireId);
        Questionnaire.setCreateDate(new Date());
        questionnaireMapper.insertSelective(Questionnaire);

        cmsQuestionnaireItemList.forEach(cmsQuestionnaireItem -> {
            cmsQuestionnaireItem.setQuestionnaireItemId(UUIDUtils.getId());
            cmsQuestionnaireItem.setQuestionnaireId(questionnaireId);
            cmsQuestionnaireItem.setCreateDate(new Date());
            questionnaireItemMapper.insertSelective(cmsQuestionnaireItem);
        });
        return questionnaireId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uptQuestionnaire(CmsQuestionnaire Questionnaire, List<CmsQuestionnaireItem> cmsQuestionnaireItemList) {
        String questionnaireId = Questionnaire.getQuestionnaireId();
        questionnaireMapper.updateByPrimaryKeySelective(Questionnaire);
        questionnaireItemMapper.delQuestionnaireItemByQuestionnaireId(questionnaireId);
        cmsQuestionnaireItemList.forEach(cmsQuestionnaireItem -> {
            cmsQuestionnaireItem.setQuestionnaireItemId(UUIDUtils.getId());
            cmsQuestionnaireItem.setQuestionnaireId(questionnaireId);
            cmsQuestionnaireItem.setCreateDate(new Date());
            questionnaireItemMapper.insertSelective(cmsQuestionnaireItem);
        });
    }

    @Override
    public void delQuestionnaire(String questionnaireId) {
        questionnaireMapper.delQuestionnaireByQuestionnaireId(questionnaireId);
    }


}
