package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IQuestionnaireService;
import com.linkpets.core.dao.CmsAdoptQuestionnaireAnswerMapper;
import com.linkpets.core.dao.CmsAdoptQuestionnaireItemMapper;
import com.linkpets.core.dao.CmsAdoptQuestionnaireMapper;
import com.linkpets.core.model.CmsAdoptQuestionnaire;
import com.linkpets.core.model.CmsAdoptQuestionnaireAnswer;
import com.linkpets.core.model.CmsAdoptQuestionnaireItem;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;
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
    private CmsAdoptQuestionnaireMapper questionnaireMapper;

    @Resource
    private CmsAdoptQuestionnaireItemMapper questionnaireItemMapper;

    @Override
    public PageInfo<CmsAdoptQuestionnaire> getQuestionnairePage(String questionnaireTitle, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptQuestionnaire> questionnaireList = questionnaireMapper.getQuestionnaireList(questionnaireTitle);
        return new PageInfo<>(questionnaireList);
    }

    @Override
    public List<CmsAdoptQuestionnaire> getQuestionnaireList(String questionnaireTitle) {
        return questionnaireMapper.getQuestionnaireList(questionnaireTitle);
    }

    @Override
    public RespQuestionnaireInfo getQuestionnaireInfo(String questionnaireId) {
        RespQuestionnaireInfo respQuestionnaireInfo = new RespQuestionnaireInfo();
        CmsAdoptQuestionnaire questionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        List<CmsAdoptQuestionnaireItem> questionnaireItemList = questionnaireItemMapper.getListByQuestionnaireId(questionnaireId);
        respQuestionnaireInfo.setQuestionnaire(questionnaire);
        respQuestionnaireInfo.setQuestionnaireItemList(questionnaireItemList);
        return respQuestionnaireInfo;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtQuestionnaire(CmsAdoptQuestionnaire adoptQuestionnaire, List<CmsAdoptQuestionnaireItem> cmsAdoptQuestionnaireItemList) {
        String questionnaireId = UUIDUtils.getId();
        adoptQuestionnaire.setQuestionnaireId(questionnaireId);
        adoptQuestionnaire.setCreateDate(new Date());
        questionnaireMapper.insertSelective(adoptQuestionnaire);

        cmsAdoptQuestionnaireItemList.forEach(cmsAdoptQuestionnaireItem -> {
            cmsAdoptQuestionnaireItem.setQuestionnaireItemId(UUIDUtils.getId());
            cmsAdoptQuestionnaireItem.setQuestionnaireId(questionnaireId);
            cmsAdoptQuestionnaireItem.setCreateDate(new Date());
            questionnaireItemMapper.insertSelective(cmsAdoptQuestionnaireItem);
        });
        return questionnaireId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uptQuestionnaire(CmsAdoptQuestionnaire adoptQuestionnaire, List<CmsAdoptQuestionnaireItem> cmsAdoptQuestionnaireItemList) {
        String questionnaireId = adoptQuestionnaire.getQuestionnaireId();
        questionnaireMapper.updateByPrimaryKeySelective(adoptQuestionnaire);
        questionnaireItemMapper.delQuestionnaireItemByQuestionnaireId(questionnaireId);
        cmsAdoptQuestionnaireItemList.forEach(cmsAdoptQuestionnaireItem -> {
            cmsAdoptQuestionnaireItem.setQuestionnaireItemId(UUIDUtils.getId());
            cmsAdoptQuestionnaireItem.setQuestionnaireId(questionnaireId);
            cmsAdoptQuestionnaireItem.setCreateDate(new Date());
            questionnaireItemMapper.insertSelective(cmsAdoptQuestionnaireItem);
        });
    }

    @Override
    public void delQuestionnaire(String questionnaireId) {
        questionnaireMapper.delQuestionnaireByQuestionnaireId(questionnaireId);
    }


}
