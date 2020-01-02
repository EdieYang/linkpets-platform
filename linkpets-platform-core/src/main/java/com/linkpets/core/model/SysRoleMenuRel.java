package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleMenuRel {
    private String id;

    private String roleId;

    private String menuId;

    private Date createDate;

    private String isValid;

}