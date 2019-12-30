package com.linkpets.cms.manage.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.SysRole;
import com.linkpets.core.respEntity.system.SysRolePermissionRes;
import com.linkpets.core.respEntity.system.SysRoleUserRes;
import com.linkpets.core.respEntity.system.SysUserRoleRes;

import java.util.List;

public interface ISysRoleService {

    /**
     * 分页查询角色列表
     *
     * @param roleName
     * @param roleCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysRole> getSysRolePage(String roleName, String roleCode, Integer pageNum, Integer pageSize);


    /**
     * 分页查询角色用户列表
     *
     * @param roleId
     * @param userAccount
     * @param userName
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysRoleUserRes> getSysRoleUserPage(String roleId, String userAccount, String userName, Integer pageNum, Integer pageSize);


    /**
     * 查询角色信息
     *
     * @param id
     * @return
     */
    SysRole getSysRole(String id);

    /**
     * 创建角色
     *
     * @param sysRole
     * @return
     */
    String crtSysRole(SysRole sysRole);

    /**
     * 更新角色
     *
     * @param sysRole
     */
    void uptSysRole(SysRole sysRole);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void delSysRole(String roleId);

    /**
     * 批量删除角色
     *
     * @param roleIdList
     */
    void batchDelSysRole(List<String> roleIdList);

    /**
     * 创建用户角色
     *
     * @param userId
     * @param roleId
     */
    void crtSysUserRole(String userId, String roleId);

    /**
     * 删除用户角色
     *
     * @param userId
     * @param roleId
     */
    void delSysUserRole(String userId, String roleId);

    /**
     * 分页查询角色权限列表
     *
     * @param roleId
     * @param permissionName
     * @param permissionCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysRolePermissionRes> getSysRolePermissionPage(String roleId, String permissionName, String permissionCode, Integer pageNum, Integer pageSize);

    /**
     * 新增角色权限
     *
     * @param roleId
     * @param permissionId
     */
    void crtSysRolePermission(String roleId, String permissionId);

    /**
     * 删除角色权限
     *
     * @param roleId
     * @param permissionId
     */
    void delSysRolePermission(String roleId, String permissionId);

    /**
     * 分页根据用户查询角色列表
     *
     * @param userId
     * @param roleName
     * @param roleCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysUserRoleRes> getSysUserRolePage(String userId, String roleName, String roleCode, Integer pageNum, Integer pageSize);

    /**
     * 新增角色菜单
     *
     * @param roleId
     * @param menus
     */
    void crtSysRoleMenus(String roleId, String menus);

    /**
     * 根据userId获取系统角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getSysRoleListByUserId(String userId);

}
