package com.linkpets.cms.adopt.reqEntity;

import com.linkpets.core.model.CmsAdoptQuestionnaire;
import com.linkpets.core.model.CmsAdoptQuestionnaireItem;
import lombok.Data;

import java.util.List;

/**
 * @author edie
 */
@Data
public class QuestionnaireReq {

    private CmsAdoptQuestionnaire questionnaire;

    private List<CmsAdoptQuestionnaireItem> questionnaireItemList;

}
