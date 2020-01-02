package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
    public class SysRolePermissionRel {
    private String id;

    private String roleId;

    private String permissionId;

    private Integer isValid;

    private Date createDate;
}