package com.linkpets.cms.adopt.service;

public interface ISignInService {

    /**
     * 用户签到返回签到积分
     *
     * @param userId
     * @return
     */
    int crtSignInRecord(String userId);

    /**
     * 获取用户连续签到次数
     */
    int getSignInTimes(String userId);


}
