package com.linkpets.cms.user.service;

import com.linkpets.cms.user.model.SignInPoints;

public interface ISignInService {

    /**
     * 用户签到返回签到积分
     *
     * @param userId
     * @return
     */
    SignInPoints crtSignInRecord(String userId);

    /**
     * 获取用户连续签到次数
     */
    int getSignInTimes(String userId);


}
