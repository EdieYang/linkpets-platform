package com.linkpets.core.respEntity.system;

import lombok.Data;

/**
 * @Author Edie
 */
@Data
public class SysRolePermissionRes {

    private String roleId;

    private String permissionId;

    private String permissionName;

    private String permissionCode;

    private String permissionDescription;

    private Integer isAdd;

}
