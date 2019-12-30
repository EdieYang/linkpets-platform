package com.linkpets.cms.manage.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.SysUser;

import java.util.List;

public interface ISysUserService {

    /**
     * 分页查询用户列表
     *
     * @param userAccount
     * @param userName
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysUser> getSysUserPage(String userAccount, String userName, Integer pageNum, Integer pageSize);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    SysUser getSysUser(String userId);

    /**
     * 根据登录账号获取用户信息
     *
     * @param userAcc
     * @return
     */
    SysUser getSysUserByUserAccount(String userAcc);

    /**
     * 根据userId查询用户
     * @param userId
     * @return
     */
    SysUser getSysUserByUserId(String userId);

    /**
     * 创建系统用户
     *
     * @param sysUser
     * @return
     */
    String crtSysUser(SysUser sysUser);

    /**
     * 更新系统用户
     *
     * @param sysUser
     */
    void uptSysUser(SysUser sysUser);

    /**
     * 删除系统用户
     *
     * @param userId
     */
    void delSysUser(String userId);

    /**
     * 批量删除系统用户
     *
     * @param userIdList
     */
    void batchDelSysUser(List<String> userIdList);
}
