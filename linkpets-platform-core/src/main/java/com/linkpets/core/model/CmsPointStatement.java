package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsPointStatement {
    private String id;

    private String userId;

    private Integer points;

    private String targetId;

    private Integer channel;

    private Date createDate;

    private Integer isValid;
}