package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysChain {

    private String chainId;

    private String chainName;

    private String chainAddress;

    private String chainPhone;

    private String chainCode;

    private String orgId;

    private String operUserId;

    private Date createTime;

    private Date updateTime;

    private Integer isValid;

}