package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptApply {

    private String applyId;

    private String petId;

    private String applyBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;

    private String sex;

    private String age;

    private String keptPet;

    private String maritalStatus;

    private String housingCondition;

    private String job;

    private String address;

    private String mobilePhone;

    private String toAdopter;

    private String applyStatus;

    private String applyResp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    private String isValid;

    private String formId;

    private String operateUserId;

}