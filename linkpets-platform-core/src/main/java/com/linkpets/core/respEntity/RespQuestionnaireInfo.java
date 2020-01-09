package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsQuestionnaire;
import com.linkpets.core.model.CmsQuestionnaireItem;
import lombok.Data;

import java.util.List;

/**
 * 问卷详情
 * @author edie
 */
@Data
public class RespQuestionnaireInfo {

    private CmsQuestionnaire questionnaire;

    private List<CmsQuestionnaireItem> questionnaireItemList;

}
