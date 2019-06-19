package com.linkpets.wechat.service;

import com.linkpets.core.model.SysUser;

public interface ISysUserService {

    SysUser getSysUserInfo(String userId);

    void insertSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

}
