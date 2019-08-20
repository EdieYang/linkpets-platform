package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptOrgFollow {

    private String id;

    private String orgId;

    private String userId;

    private Date createDate;

    private Integer isValid;

}
