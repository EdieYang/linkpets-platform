package com.linkpets.wechat.service.impl;

import com.linkpets.core.dao.UserTempMapper;
import com.linkpets.core.model.UserTemp;
import com.linkpets.wechat.service.IUserTempService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserTempServiceImpl implements IUserTempService {


    @Resource
    private UserTempMapper userTempMapper;


    @Override
    public UserTemp getTempUserByOpenId(String openId) {
        return userTempMapper.getTempUserByOpenId(openId);
    }

    @Override
    public void insertUserTemp(UserTemp userTemp) {
        userTempMapper.insertSelective(userTemp);
    }
}
