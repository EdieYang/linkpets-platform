package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoute {
    private String id;

    private String parentId;

    private String title;

    private String path;

    private String name;

    private String component;

    private String componentPath;

    private Integer cache;

    private Integer sort;

    private Date createDate;

    private String isValid;

}