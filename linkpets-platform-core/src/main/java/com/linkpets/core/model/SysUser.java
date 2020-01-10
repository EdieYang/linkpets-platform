package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private String userId;

    private String userAccount;

    private String password;

    private String userName;

    private String userPortrait;

    private String mobilePhone;

    private String email;

    private Integer isActive;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private Integer isValid;

}