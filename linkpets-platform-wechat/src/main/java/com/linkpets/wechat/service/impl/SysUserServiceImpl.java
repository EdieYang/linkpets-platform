package com.linkpets.wechat.service.impl;


import com.linkpets.core.dao.SysUserMapper;
import com.linkpets.core.model.SysUser;
import com.linkpets.wechat.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl  implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getSysUserInfo(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void insertSysUser(SysUser sysUser) {
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }
}
