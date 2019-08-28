package com.linkpets.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.core.dao.CmsUserLoginMapper;
import com.linkpets.core.dao.CmsUserMapper;
import com.linkpets.core.dao.UserTempMapper;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.CmsUserLogin;
import com.linkpets.core.model.UserTemp;
import com.linkpets.util.UUIDUtils;
import com.linkpets.wechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    private static int userIdGrenc = -1;

    private static int year = -1;

    private final static String USER_ID_TEMP = "hu-03+区号+年份（后两位）+00001";

    @Autowired
    private CmsUserMapper userMapper;
    @Autowired
    private UserTempMapper userTempMapper;
    @Autowired
    private CmsUserLoginMapper userLoginMapper;


    @Override
    public int modifyUser(CmsUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public CmsUser getUserInfo(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserTemp getTempUserByOpenId(String openId) {
        return userTempMapper.getTempUserByOpenId(openId);
    }

    @Override
    public boolean insertUserTemp(UserTemp userTemp) {
        return userTempMapper.insertSelective(userTemp) > 0;
    }

    @Override
    public boolean insertUser(CmsUser user) {
        return userMapper.insertSelective(user) > 0;
    }

    @Override
    public CmsUser getUserByUnionId(String unionId) {
        return userMapper.getUserByUnionId(unionId);
    }

    @Override
    public boolean modifyUserTemp(String userId) {
        UserTemp userTemp = new UserTemp();
        userTemp.setUserTempId(userId);
        return userTempMapper.updateByPrimaryKeySelective(userTemp) > 0;
    }

    @Override
    public String getOpenIdByUserId(String userId) {
        return userTempMapper.selectByPrimaryKey(userId).getOpenId();
    }

    @Override
    public void recordUserLastLogin(String userId) {
        CmsUserLogin userLogin = new CmsUserLogin();
        userLogin.setLoginDate(new Date());
        userLogin.setUserId(userId);
        userLogin.setLoginId(UUIDUtils.randomUUID());
        userLoginMapper.insertSelective(userLogin);
    }

}
