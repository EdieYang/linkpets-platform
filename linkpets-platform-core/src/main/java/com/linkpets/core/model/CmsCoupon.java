package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsCoupon {

    private String couponId;

    private String couponName;

    private String couponEnName;

    private Integer couponType;

    private String couponRule;

    private String couponRemind;

    private String orgId;

    private Date createTime;

    private Integer isValid;

}