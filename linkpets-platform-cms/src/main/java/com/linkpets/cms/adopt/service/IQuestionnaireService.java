package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptQuestionnaire;
import com.linkpets.core.model.CmsAdoptQuestionnaireAnswer;
import com.linkpets.core.model.CmsAdoptQuestionnaireItem;
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
    PageInfo<CmsAdoptQuestionnaire> getQuestionnairePage(String questionnaireTitle, Integer pageNum, Integer pageSize);


    /**
     * 查询问卷列表
     *
     * @param questionnaireTitle
     * @return
     */
    List<CmsAdoptQuestionnaire> getQuestionnaireList(String questionnaireTitle);


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
    String crtQuestionnaire(CmsAdoptQuestionnaire adoptQuestionnaire, List<CmsAdoptQuestionnaireItem> cmsAdoptQuestionnaireItemList);

    /**
     * 更新问卷
     *
     * @param adoptQuestionnaire
     * @param cmsAdoptQuestionnaireItemList
     */
    void uptQuestionnaire(CmsAdoptQuestionnaire adoptQuestionnaire, List<CmsAdoptQuestionnaireItem> cmsAdoptQuestionnaireItemList);

    /**
     * 删除问卷
     *
     * @param questionnaireId
     */
    void delQuestionnaire(String questionnaireId);


}
