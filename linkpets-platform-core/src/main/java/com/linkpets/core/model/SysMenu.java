package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {
    private String id;

    private String parentId;

    private String title;

    private String path;

    private String icon;

    private Integer sort;

    private Date createDate;

    private String isValid;

}