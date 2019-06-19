package com.linkpets.wechat.dao;

import com.linkpets.core.model.UserTemp;

public interface UserTempCustomMapper {

    UserTemp getTempUserByOpenId(String openId);
}
