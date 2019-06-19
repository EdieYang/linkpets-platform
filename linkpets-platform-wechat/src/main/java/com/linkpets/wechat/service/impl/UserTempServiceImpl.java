package com.linkpets.wechat.service.impl;

import com.linkpets.core.dao.UserTempMapper;
import com.linkpets.core.model.UserTemp;
import com.linkpets.wechat.dao.UserTempCustomMapper;
import com.linkpets.wechat.service.IUserTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserTempServiceImpl implements IUserTempService {

    @Autowired
    private UserTempCustomMapper userTempCustomMapper;

    @Autowired
    private UserTempMapper userTempMapper;


    @Override
    public UserTemp getTempUserByOpenId(String openId) {
        return userTempCustomMapper.getTempUserByOpenId(openId);
    }

    @Override
    public void insertUserTemp(UserTemp userTemp) {
        userTempMapper.insertSelective(userTemp);
    }
}
