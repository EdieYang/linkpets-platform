package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {


	private String userId;

    private String userName;
    
    private String userAcc;

    private String password;
    
    private String refUserId;

    private String roleId;

    private Integer isActive;

    private String chainId;

    private String unionId;
    
    private Date createTime;

    private Date updateTime;

    private Integer isValid;
    
    private String roleType;

    private String orgId;

    private String orgName;

    private String chainName;


}