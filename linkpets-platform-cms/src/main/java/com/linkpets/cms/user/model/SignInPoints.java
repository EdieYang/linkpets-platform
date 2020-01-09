package com.linkpets.cms.user.model;

import lombok.Data;

/**
 * 签到积分
 *
 * @author Gaga
 */
@Data
public class SignInPoints {

    private Integer points;

    private Integer groupDays;

    public SignInPoints() {
        this.groupDays = 0;
        this.points = 0;
    }
}
