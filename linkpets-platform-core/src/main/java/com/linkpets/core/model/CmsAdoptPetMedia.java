package com.linkpets.core.model;

import lombok.Data;

@Data
public class CmsAdoptPetMedia {

    private Integer mediaId;

    private String petId;

    private String mediaType;

    private String mediaPath;

    private Integer isValid;

}