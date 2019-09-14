package com.linkpets.core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CmsAdoptOrgActivity {

    private String id;

    private String orgId;

    private String title;

    private String activityPath;

    private String activityCover;

    private Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private Integer isValid;
}
