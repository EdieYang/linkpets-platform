package com.linkpets.core.respEntity;

import com.linkpets.core.model.SysRole;
import lombok.Data;

import java.util.List;

/**
 * 所属组织用户
 *
 * @author edie
 */
@Data
public class RespOrgUser {

    private String id;

    private String userId;

    private String sysUserId;

    private String wxAccount;

    private String nickName;

    private String mobilePhone;

    private Integer isAdd;

    private List<SysRole> sysRoleList;

}
