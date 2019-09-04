package com.linkpets.cms.manage.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.SysUser;

/**
 * @author SteveYang
 * @date 2019/3/15
 */
public interface ISysUserService {

    /**
     * 查询系统用户账号列表
     * @param orgId
     * @param chainId
     * @param pageNo
     * @param pageSize
     * @return
     */
    JSONObject listSysUser(String orgId, String chainId, int pageNo, int pageSize);

    /**
     * 系统用户账号登录
     * @param userName
     * @param password
     * @return
     */
    JSONObject loginSysUser(String userName, String password);

    SysUser getSysUserByUserName(String userName);

    SysUser getSysUser(String userId);

    SysUser updateSysUser(String userId,String chainId);

    void register(String userName, String password);

}
