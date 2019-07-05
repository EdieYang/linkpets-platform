package com.linkpets.cms.adopt.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.model.UserInfo;
import com.linkpets.core.model.CmsAdoptAttention;
import com.linkpets.core.model.CmsUser;

public interface IUserService {

    UserInfo getUserInfoByUserId(String userId);

    void updateUserInfo(CmsUser user);

    String getLastLoginTime(String userId);

    int getLoginCount(String syncDate);

	JSONObject getUserListAttentTo(Map<String, Object> param, int pageNum, int pageSize, String string);

	JSONObject getUserListAttentBy(Map<String, Object> param, int pageNum, int pageSize, String string);

	int getAttentionStatus(String userId,String targetUserId);

	void crtAttention(CmsAdoptAttention record);

	void delAttention(String userId, String attentBy);

	Map<String,Object> getUserAddition(String userId);

	int getTotalUserCount();
}
