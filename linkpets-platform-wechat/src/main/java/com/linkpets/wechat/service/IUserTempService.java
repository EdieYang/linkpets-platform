package com.linkpets.wechat.service;

import com.linkpets.core.model.UserTemp;

public interface IUserTempService {


    UserTemp getTempUserByOpenId(String openId);

    void insertUserTemp(UserTemp userTemp);

}
