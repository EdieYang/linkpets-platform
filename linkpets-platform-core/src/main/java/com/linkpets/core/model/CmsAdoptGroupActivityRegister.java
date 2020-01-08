package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptGroupActivityRegister {
    private String registerId;

    private String activityId;

    private String userId;

    private String involvementTime;

    private Integer isPaid;

    private Integer paymentAmount;

    private String memo;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

}