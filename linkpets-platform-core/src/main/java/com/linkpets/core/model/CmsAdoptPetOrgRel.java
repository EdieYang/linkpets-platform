package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptPetOrgRel {

    private String id;

    private String orgId;

    private String petId;

    private Date createDate;

    private Integer isValid;

}