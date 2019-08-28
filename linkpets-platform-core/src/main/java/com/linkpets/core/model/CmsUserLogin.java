package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsUserLogin {

    private String loginId;

    private String userId;

    private Date loginDate;

}