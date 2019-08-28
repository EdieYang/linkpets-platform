package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptPetCollect {

    private Integer id;

    private String petId;

    private String userId;

    private Date collectTime;

    private Integer isValid;

}