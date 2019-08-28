package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptReport {

    private String reportId;

    private String petId;

    private String reportType;

    private String reportStatus;

    private String memo;

    private String reportUserId;

    private Date createDate;

    private Integer isValid;

}