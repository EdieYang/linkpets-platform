package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private String roleId;

    private String roleName;

    private String roleCode;

    private String roleDescription;

    private Date createDate;

    private String isValid;

}