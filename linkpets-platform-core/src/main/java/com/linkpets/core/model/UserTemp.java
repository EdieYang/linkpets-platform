package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserTemp {

    private String userTempId;

    private String openId;

    private Integer isValid;

    private Date createDate;

}