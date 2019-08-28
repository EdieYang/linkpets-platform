package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptOrg {

    private String orgId;

    private String orgName;

    private String title;

    private String brief;

    private String logo;

    private String coverImg;

    private String postImg;

    private Date createDate;

    private Integer isValid;

}