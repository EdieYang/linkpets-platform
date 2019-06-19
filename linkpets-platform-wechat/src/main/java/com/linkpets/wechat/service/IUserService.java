package com.linkpets.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.UserTemp;

import java.util.Map;

public interface IUserService {

	/**
	 * 生成用户ID，格式为"hu-03+区号+年份（后两位）+00001"
	 *
	 * @param areaId
	 * @return
	 */
	String createUserId(String areaId);

	int modifyUser(CmsUser user);

	CmsUser getUserInfo(String userId);

	/**
	 * 根据查询条件获取用户列表（分页）
	 * @param param
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	JSONObject getUserList(Map<String, Object> param, int pageNum, int pageSize);

	UserTemp getTempUserByOpenId(String openId);

	boolean insertUserTemp(UserTemp userTemp);

	boolean insertUser(CmsUser user);

	CmsUser getUserByUnionId(String unionId);

	boolean modifyUserTemp(String userId);

	String getOpenIdByUserId(String userId);

}
