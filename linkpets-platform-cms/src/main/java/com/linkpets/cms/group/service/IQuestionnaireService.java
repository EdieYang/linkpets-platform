package com.linkpets.cms.group.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsQuestionnaire;
import com.linkpets.core.model.CmsQuestionnaireAnswer;
import com.linkpets.core.model.CmsQuestionnaireItem;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;
import com.linkpets.core.respEntity.RespQuestionnaireInfo;

import java.util.List;

public interface IQuestionnaireService {

    /**
     * 分页查询问卷列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CmsQuestionnaire> getQuestionnairePage(String questionnaireTitle, Integer pageNum, Integer pageSize);


    /**
     * 查询问卷列表
     *
     * @param questionnaireTitle
     * @return
     */
    List<CmsQuestionnaire> getQuestionnaireList(String questionnaireTitle);


    /**
     * 获取问卷详情
     *
     * @param questionnaireId
     * @return
     */
    RespQuestionnaireInfo getQuestionnaireInfo(String questionnaireId);


    /**
     * 创建问卷
     *
     * @param adoptQuestionnaire
     * @param cmsAdoptQuestionnaireItemList
     * @return
     */
    String crtQuestionnaire(CmsQuestionnaire adoptQuestionnaire, List<CmsQuestionnaireItem> cmsAdoptQuestionnaireItemList);

    /**
     * 更新问卷
     *
     * @param adoptQuestionnaire
     * @param cmsAdoptQuestionnaireItemList
     */
    void uptQuestionnaire(CmsQuestionnaire adoptQuestionnaire, List<CmsQuestionnaireItem> cmsAdoptQuestionnaireItemList);

    /**
     * 删除问卷
     *
     * @param questionnaireId
     */
    void delQuestionnaire(String questionnaireId);


}
