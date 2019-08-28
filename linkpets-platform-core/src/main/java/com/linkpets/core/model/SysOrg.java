package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysOrg {

    private String orgId;

    private String orgName;

    private String orgCode;

    private String orgLogo;

    private String operUserId;

    private Date createTime;

    private Date updateTime;

    private Integer isValid;

}