package com.linkpets.core.respEntity;

import lombok.Data;

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

}
