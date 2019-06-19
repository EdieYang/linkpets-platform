package com.linkpets.cms.manage.model;

import com.linkpets.core.model.SysUser;
import lombok.Data;

@Data
public class SysUserCustom  extends SysUser {


    private String roleType;

    private String orgId;

    private String orgName;

    private String chainName;


}
