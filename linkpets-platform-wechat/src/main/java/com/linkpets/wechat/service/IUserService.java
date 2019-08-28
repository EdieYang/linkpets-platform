package com.linkpets.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.UserTemp;

import java.util.Map;

public interface IUserService {


	int modifyUser(CmsUser user);

	CmsUser getUserInfo(String userId);

	UserTemp getTempUserByOpenId(String openId);

	boolean insertUserTemp(UserTemp userTemp);

	boolean insertUser(CmsUser user);

	CmsUser getUserByUnionId(String unionId);

	boolean modifyUserTemp(String userId);

	String getOpenIdByUserId(String userId);

	void recordUserLastLogin(String userId);

}
