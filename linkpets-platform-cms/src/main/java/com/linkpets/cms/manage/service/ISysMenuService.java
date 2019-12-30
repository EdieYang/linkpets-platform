package com.linkpets.cms.manage.service;

import com.linkpets.core.model.SysMenu;
import com.linkpets.core.respEntity.system.SysMenuRes;

import java.util.List;

public interface ISysMenuService {

    List<SysMenuRes> getSysMenuList();

    SysMenu getSusMenuById(String id);

    String crtSysMenu(SysMenu sysMenu);

    void uptSysMenu(SysMenu sysMenu);

    void delSysMenu(String menuId);

    /**
     * 查询角色菜单列表
     *
     * @param roleId
     * @return
     */
    List<SysMenu> getSysRoleMenuList(String roleId);

    /**
     * 根据userId获取系统菜单
     *
     * @param userId
     * @return
     */
    List<SysMenuRes> getSysMenuListByUserId(String userId);

}
