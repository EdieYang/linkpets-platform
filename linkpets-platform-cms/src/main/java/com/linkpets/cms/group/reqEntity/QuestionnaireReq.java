package com.linkpets.cms.group.reqEntity;

import com.linkpets.core.model.CmsQuestionnaire;
import com.linkpets.core.model.CmsQuestionnaireItem;
import lombok.Data;

import java.util.List;

/**
 * @author edie
 */
@Data
public class QuestionnaireReq {

    private CmsQuestionnaire questionnaire;

    private List<CmsQuestionnaireItem> questionnaireItemList;

}
