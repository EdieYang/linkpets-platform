package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysPermission {
    private String id;

    private String permissionName;

    private String permissionCode;

    private String permissionDescription;

    private Integer isValid;

    private Date createDate;
}