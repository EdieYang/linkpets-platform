package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptOrgGallery {

    private String id;

    private String orgId;

    private String image;

    private int isValid;

    private Date createDate;

}
