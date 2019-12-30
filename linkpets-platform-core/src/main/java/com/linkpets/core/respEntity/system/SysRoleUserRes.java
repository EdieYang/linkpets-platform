package com.linkpets.core.respEntity.system;

import lombok.Data;

/**
 * @Author Edie
 */
@Data
public class SysRoleUserRes {

    private String userId;

    private String roleId;

    private String userAccount;

    private String userName;

    private String email;

    private String mobilePhone;

    private Integer isAdd;

}
