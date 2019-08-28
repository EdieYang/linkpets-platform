package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsUser {

    private String userId;

    private String nickName;

    private String mobilePhone;

    private String portrait;

    private String unionId;

    private Date birthday;

    private String gender;

    private String location;

    private String intro;

    /**
     * 是否实名（0：未实名 1：已实名）
     */
    private Integer authenticated;

    private String openid;

    private int followed;

    private Date createDate;

    private Integer isValid;

    /**
     * 星座
     */
    private String starSign;

    /**
     * 最近一次登录时间
     */
    private String lastLoginTime;

    /**
     * 几0后
     */
    private String ageFrom;

    /**
     * 关注用户数
     */
    private int followNum;

    /**
     * 被关注用户数
     */
    private int followByNum;

    /**
     * 私信回复率
     */
    private String chatResp;

    /**
     * 待领养数
     */
    private int adoptingNum;

    /**
     * 已领养数
     */
    private int adoptedNum;

    /**
     * 申请领养处理率
     */
    private String applyHandle;

}