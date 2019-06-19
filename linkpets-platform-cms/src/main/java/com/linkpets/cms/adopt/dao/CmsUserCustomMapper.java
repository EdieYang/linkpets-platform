package com.linkpets.cms.adopt.dao;

import com.linkpets.cms.adopt.model.UserInfo;

public interface CmsUserCustomMapper {

    UserInfo getUserInfoByUserId(String userId);
}
