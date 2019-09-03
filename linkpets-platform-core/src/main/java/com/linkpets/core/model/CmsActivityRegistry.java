package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsActivityRegistry {

    private String registryId;

    private String activityId;

    private String userId;

    private Integer registryType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer isValid;

    private String activityName;

    private String introduction;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String orgId;

    private String orgName;

    private Integer isActive;

    private String userName;

    private String mobilePhone;

    private String photoPath;

    private String unionId;

}