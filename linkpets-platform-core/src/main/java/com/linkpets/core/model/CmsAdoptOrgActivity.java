package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptOrgActivity {

    private String id;

    private String orgId;

    private String title;

    private String activityPath;

    private String activityCover;

    private Integer sort;

    private Date createDate;

    private Integer isValid;
}
