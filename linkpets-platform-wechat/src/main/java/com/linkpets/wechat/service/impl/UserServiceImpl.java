package com.linkpets.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.core.dao.CmsUserMapper;
import com.linkpets.core.dao.UserTempMapper;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.UserTemp;
import com.linkpets.wechat.dao.CmsUserCustomMapper;
import com.linkpets.wechat.dao.UserTempCustomMapper;
import com.linkpets.wechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

	private static int userIdGrenc = -1;

	private static int year = -1;

	private final static String USER_ID_TEMP = "hu-03+区号+年份（后两位）+00001";

	@Autowired
	private CmsUserCustomMapper userCustomMapper;


	@Autowired
	private CmsUserMapper userMapper;

	@Autowired
	private UserTempMapper userTempMapper;

	@Autowired
	private UserTempCustomMapper userTempCustomMapper;

	@Override
	public String createUserId(String areaId) {
		Calendar cal = Calendar.getInstance();

		if (cal.get(Calendar.YEAR) != year) {
			year = cal.get(Calendar.YEAR);
			String maxId = userCustomMapper.getMaxUserNo(USER_ID_TEMP.substring(0, 3), (year + "").substring(2));
			userIdGrenc = null == maxId ? 1 : Integer.parseInt(maxId) + 1;
			return createUserId(areaId);
		} else {
			String userIdEnd = "000000" + userIdGrenc++;
			return USER_ID_TEMP.substring(0, 3) + areaId + ("" + year).substring(2, 4)
					+ userIdEnd.substring(userIdEnd.length() - 6, userIdEnd.length());
		}

	}

	@Override
	public int modifyUser(CmsUser user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public CmsUser getUserInfo(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public JSONObject getUserList(Map<String, Object> param, int pageNum, int pageSize) {
		JSONObject result = new JSONObject();
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = userCustomMapper.selectUserList(param);
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		result.put("page", page.getPageNum());
		result.put("records", page.getTotal());
		result.put("rows", list);
		return result;
	}

	@Override
	public UserTemp getTempUserByOpenId(String openId) {
		return userTempCustomMapper.getTempUserByOpenId(openId);
	}

	@Override
	public boolean insertUserTemp(UserTemp userTemp) {
		return userTempMapper.insertSelective(userTemp)>0;
	}

	@Override
	public boolean insertUser(CmsUser user) {
		return  userMapper.insertSelective(user)>0;
	}

	@Override
	public CmsUser getUserByUnionId(String unionId) {
		return userCustomMapper.getUserByUnionId(unionId);
	}

	@Override
	public boolean modifyUserTemp(String userId) {
		UserTemp userTemp=new UserTemp();
		userTemp.setUserTempId(userId);
		return userTempMapper.updateByPrimaryKeySelective(userTemp)>0;
	}

	@Override
	public String getOpenIdByUserId(String userId) {
		return userTempMapper.selectByPrimaryKey(userId).getOpenId();
	}

}
