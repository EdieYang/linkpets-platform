package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsActivityAssistance {

    private String assistId;

    private String activityId;

    private String userId;

    private Integer assistType;

    private String assistUserId;

    private Date createTime;

    private Integer isValid;

    private Integer assistOrder;


}