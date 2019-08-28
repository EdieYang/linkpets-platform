package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {

    private String roleId;

    private String roleType;

    private String orgId;

    private Date createTime;

    private Integer isValid;

}