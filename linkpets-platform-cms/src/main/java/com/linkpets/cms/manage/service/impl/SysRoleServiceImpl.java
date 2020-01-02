package com.linkpets.cms.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.manage.service.ISysRoleService;
import com.linkpets.core.dao.SysRoleMapper;
import com.linkpets.core.dao.SysRoleMenuRelMapper;
import com.linkpets.core.dao.SysRolePermissionRelMapper;
import com.linkpets.core.dao.SysUserRoleRelMapper;
import com.linkpets.core.model.SysRole;
import com.linkpets.core.model.SysRoleMenuRel;
import com.linkpets.core.model.SysRolePermissionRel;
import com.linkpets.core.model.SysUserRoleRel;
import com.linkpets.core.respEntity.system.SysRolePermissionRes;
import com.linkpets.core.respEntity.system.SysRoleUserRes;
import com.linkpets.core.respEntity.system.SysUserRoleRes;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleRelMapper sysUserRoleRelMapper;
    @Resource
    private SysRolePermissionRelMapper sysRolePermissionRelMapper;
    @Resource
    private SysRoleMenuRelMapper sysRoleMenuRelMapper;

    @Override
    public PageInfo<SysRole> getSysRolePage(String roleName, String roleCode, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> roleList = sysRoleMapper.getSysRoleList(roleName, roleCode);
        return new PageInfo<>(roleList);
    }

    @Override
    public PageInfo<SysRoleUserRes> getSysRoleUserPage(String roleId, String userAccount, String userName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRoleUserRes> roleUserList = sysUserRoleRelMapper.getSysRoleUserPage(roleId, userAccount, userName);
        return new PageInfo<>(roleUserList);
    }

    @Override
    public SysRole getSysRole(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysRole getSysRoleByRoleCode(String roleCode) {
        return sysRoleMapper.getSysRoleByRoleCode(roleCode);
    }

    @Override
    public String crtSysRole(SysRole sysRole) {
        String roleId = UUIDUtils.getId();
        sysRole.setRoleId(roleId);
        sysRole.setCreateDate(new Date());
        sysRoleMapper.insertSelective(sysRole);
        return roleId;
    }

    @Override
    public void uptSysRole(SysRole sysRole) {
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public void delSysRole(String roleId) {
        sysRoleMapper.delSysRole(roleId);
    }

    @Override
    public void batchDelSysRole(List<String> roleIdList) {
        sysRoleMapper.batchDelSysRole(roleIdList);
    }

    @Override
    public void crtSysUserRole(String userId, String roleId) {
        SysUserRoleRel sysUserRoleRel = sysUserRoleRelMapper.selectByUserIdAndRoleId(userId, roleId);
        if (sysUserRoleRel == null) {
            sysUserRoleRel = new SysUserRoleRel();
            sysUserRoleRel.setId(UUIDUtils.getId());
            sysUserRoleRel.setCreateDate(new Date());
            sysUserRoleRel.setRoleId(roleId);
            sysUserRoleRel.setUserId(userId);
            sysUserRoleRelMapper.insertSelective(sysUserRoleRel);
        }
    }

    @Override
    public void delSysUserRole(String userId, String roleId) {
        SysUserRoleRel sysUserRoleRel = sysUserRoleRelMapper.selectByUserIdAndRoleId(userId, roleId);
        if (sysUserRoleRel != null) {
            sysUserRoleRel.setIsValid(0);
            sysUserRoleRelMapper.updateByPrimaryKeySelective(sysUserRoleRel);
        }
    }

    @Override
    public PageInfo<SysRolePermissionRes> getSysRolePermissionPage(String roleId, String permissionName, String permissionCode, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRolePermissionRes> sysRolePermissionResList = sysRolePermissionRelMapper.getSysRolePermissionList(roleId, permissionName, permissionCode);
        return new PageInfo<>(sysRolePermissionResList);
    }

    @Override
    public void crtSysRolePermission(String roleId, String permissionId) {
        SysRolePermissionRel sysRolePermissionRel = sysRolePermissionRelMapper.selectByPermissionIdAndRoleId(permissionId, roleId);
        if (sysRolePermissionRel == null) {
            sysRolePermissionRel = new SysRolePermissionRel();
            sysRolePermissionRel.setId(UUIDUtils.getId());
            sysRolePermissionRel.setCreateDate(new Date());
            sysRolePermissionRel.setRoleId(roleId);
            sysRolePermissionRel.setPermissionId(permissionId);
            sysRolePermissionRelMapper.insertSelective(sysRolePermissionRel);
        }
    }

    @Override
    public void delSysRolePermission(String roleId, String permissionId) {
        SysRolePermissionRel sysRolePermissionRel = sysRolePermissionRelMapper.selectByPermissionIdAndRoleId(permissionId, roleId);
        if (sysRolePermissionRel != null) {
            sysRolePermissionRel.setIsValid(0);
            sysRolePermissionRelMapper.updateByPrimaryKeySelective(sysRolePermissionRel);
        }
    }

    @Override
    public PageInfo<SysUserRoleRes> getSysUserRolePage(String userId, String roleName, String roleCode, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUserRoleRes> sysRoleUserList = sysUserRoleRelMapper.getSysUserRolePage(userId, roleName, roleCode);
        return new PageInfo<>(sysRoleUserList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void crtSysRoleMenus(String roleId, String menus) {
        //清除原有菜单
        sysRoleMenuRelMapper.delAllMenusByRoleId(roleId);
        if (StringUtils.isNotEmpty(menus)) {
            List<String> menuList = Arrays.asList(menus.split(","));
            List<SysRoleMenuRel> roleMenuRelList = new ArrayList<>();
            menuList.forEach(menuId -> {
                SysRoleMenuRel roleMenuRel = new SysRoleMenuRel();
                roleMenuRel.setId(UUIDUtils.getId());
                roleMenuRel.setRoleId(roleId);
                roleMenuRel.setMenuId(menuId);
                roleMenuRel.setCreateDate(new Date());
                roleMenuRelList.add(roleMenuRel);
            });
            sysRoleMenuRelMapper.insertBatch(roleMenuRelList);
        }
    }

    @Override
    public List<SysRole> getSysRoleListByUserId(String userId) {
        return sysRoleMapper.getSysRoleListByUserId(userId);
    }

}
