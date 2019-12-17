package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsAdoptQuestionnaire;
import com.linkpets.core.model.CmsAdoptQuestionnaireItem;
import lombok.Data;

import java.util.List;

/**
 * 问卷详情
 * @author edie
 */
@Data
public class RespQuestionnaireInfo {

    private CmsAdoptQuestionnaire questionnaire;

    private List<CmsAdoptQuestionnaireItem> questionnaireItemList;

}
