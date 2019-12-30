package com.linkpets.core.respEntity.system;

import lombok.Data;

/**
 * @Author Edie
 */
@Data
public class SysUserRoleRes {

    private String roleId;

    private String roleName;

    private String roleCode;

    private String roleDescription;

    private String userId;

    private Integer isAdd;

}
