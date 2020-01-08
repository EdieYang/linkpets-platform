package com.linkpets.core.respEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RespActivityRegister {

    private String activityId;

    private String activityTitle;

    private String userId;

    private String nickName;

    private String wxAccount;

    private String mobilePhone;

    private Integer isPaid;

    private Integer paymentAmount;

    private String involvementTime;

    private String memo;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

}
