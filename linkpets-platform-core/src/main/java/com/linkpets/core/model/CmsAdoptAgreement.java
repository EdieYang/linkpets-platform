package com.linkpets.core.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptAgreement {

    private String agreementId;

    private String applyId;

    private String adopterId;

    private String petId;

    private String applyBy;

    private String agreement;

    private String adopterName;

    private String adopterIdcard;

    private String adopterAddress;

    private String adopterPhone;

    private String adopterSign;

    private String applyName;

    private String applyIdcard;

    private String applyAddress;

    private String applyPhone;

    private String applySign;

    private String signStatus;

    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Date signTime;

    private String createBy;

    private Date createDate;

    private Integer isValid;

    private String formId;

    private String operateUserId;

    private String idCardBackUrl;

    private String idCardFrontUrl;

}