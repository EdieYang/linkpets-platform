package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsActivityDraft {

    private String draftId;

    private String userId;

    private Integer draftStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date draftTime;

    private String presentType;

    private String presentId;

    private String activityId;

    private Date createTime;

    private Integer isValid;

    private String presentName;

    private String publishOrgName;

    private int couponType;

}