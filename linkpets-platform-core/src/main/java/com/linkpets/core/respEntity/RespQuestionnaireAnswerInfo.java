package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsQuestionnaireAnswer;
import lombok.Data;

/**
 * 问卷答案
 *
 * @author edie
 */
@Data
public class RespQuestionnaireAnswerInfo extends CmsQuestionnaireAnswer {

    private String nickName;

    private String mobilePhone;

    private String wechat;

    private String portrait;

}
