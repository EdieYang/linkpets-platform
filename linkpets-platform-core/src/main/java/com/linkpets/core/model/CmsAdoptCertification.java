package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptCertification {

    private String id;

    private String userId;

    private String idCard;

    private String imageFront;

    private String imageBack;

    private String status;

    private String memo;

    private Integer isValid;

    private Date createDate;

    private String formId;

    private String realName;

}