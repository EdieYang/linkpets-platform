package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsBanner {

    private String bannerId;

    private String activityId;

    private String bannerImgUrl;

    private String bannerRedirectUrl;

    private Integer sort;

    private Date createTime;

    private Integer isValid;

}