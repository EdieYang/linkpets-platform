package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CmsQuestionnaireItem {
    private String questionnaireItemId;

    private String questionnaireId;

    private String questionnaireItemTitle;

    private Integer questionnaireItemType;

    private String questionnaireItemContent;

    private Integer sort;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    public String getQuestionnaireItemId() {
        return questionnaireItemId;
    }

    public void setQuestionnaireItemId(String questionnaireItemId) {
        this.questionnaireItemId = questionnaireItemId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireItemTitle() {
        return questionnaireItemTitle;
    }

    public void setQuestionnaireItemTitle(String questionnaireItemTitle) {
        this.questionnaireItemTitle = questionnaireItemTitle;
    }

    public Integer getQuestionnaireItemType() {
        return questionnaireItemType;
    }

    public void setQuestionnaireItemType(Integer questionnaireItemType) {
        this.questionnaireItemType = questionnaireItemType;
    }

    public String getQuestionnaireItemContent() {
        return questionnaireItemContent;
    }

    public void setQuestionnaireItemContent(String questionnaireItemContent) {
        this.questionnaireItemContent = questionnaireItemContent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}