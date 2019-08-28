package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsActivityShare {

    private String activityId;

    private String shareImg;

    private String shareTitle;

    private Date createTime;

    private Integer isValid;

}