package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptFormid {

    private String id;

    private String formId;

    private String userId;

    private Integer isValid;

    private Date createTime;

    private Date expireTime;

}