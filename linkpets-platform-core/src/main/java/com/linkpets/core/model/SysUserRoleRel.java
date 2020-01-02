package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserRoleRel {
    private String id;

    private String userId;

    private String roleId;

    private Integer isValid;

    private Date createDate;

}