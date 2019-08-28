package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CmsAdoptPet {

    private String petId;

    private String petName;

    private String petAge;

    private String petType;

    private String petSex;

    private String petSterilization;

    private String petVaccine;

    private String petParasite;

    private String petFrom;

    private String petSomatotype;

    private String petHair;

    private String petCharacteristic;

    private String adoptRequirements;

    private String address;

    private String wxId;

    private String mobilePhone;

    private String adoptStatus;

    private String memo;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private String isValid;

    private String story;

    private List<CmsAdoptPetMedia> mediaList;

    private boolean collected;

    private String formId;

    private String orgId;

    private String orgName;

}