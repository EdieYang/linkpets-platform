package com.linkpets.cms.manage.service.impl;

import com.linkpets.cms.manage.service.ISysMenuService;
import com.linkpets.core.dao.SysMenuMapper;
import com.linkpets.core.model.SysMenu;
import com.linkpets.core.respEntity.system.SysMenuRes;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuRes> getSysMenuList() {
        List<SysMenu> sysMenuList = sysMenuMapper.getSysMenuListByParentId("0");
        List<SysMenuRes> sysMenuRespList = new ArrayList<>();
        sysMenuList.forEach(sysMenu -> {
            SysMenuRes sysMenuResp = new SysMenuRes(sysMenu.getId(), sysMenu.getParentId(), sysMenu.getTitle(), sysMenu.getPath(), sysMenu.getIcon(), sysMenu.getSort());
            List<SysMenu> sysMenuChildrenList = sysMenuMapper.getSysMenuListByParentId(sysMenu.getId());
            sysMenuResp.setChildren(sysMenuChildrenList);
            sysMenuRespList.add(sysMenuResp);
        });
        return sysMenuRespList;
    }

    @Override
    public SysMenu getSusMenuById(String id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public String crtSysMenu(SysMenu sysMenu) {
        String menuId = UUIDUtils.getId();
        sysMenu.setId(menuId);
        sysMenu.setCreateDate(new Date());
        sysMenuMapper.insertSelective(sysMenu);
        return menuId;
    }

    @Override
    public void uptSysMenu(SysMenu sysMenu) {
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }

    @Override
    public void delSysMenu(String menuId) {
        sysMenuMapper.delSysMenu(menuId);
    }

    @Override
    public List<SysMenu> getSysRoleMenuList(String roleId) {
        List<SysMenu> sysMenuList = sysMenuMapper.getSysRoleMenuList(roleId);
        return sysMenuList;
    }

    @Override
    public List<SysMenuRes> getSysMenuListByUserId(String userId) {
        List<SysMenuRes> sysMenuResList = new ArrayList<>();
        List<SysMenu> sysMenuList = sysMenuMapper.getSysMenuListByUserId(userId);
        Set<String> parentIdList = new HashSet<>();
        sysMenuList.forEach(sysMenu -> {
            if (!"0".equals(sysMenu.getParentId())) {
                parentIdList.add(sysMenu.getParentId());
            }
        });
        if (parentIdList.size() == 0) {
            return sysMenuResList;
        }
        List<SysMenu> parentMenuList = sysMenuMapper.getSysMenuListByParentIds(new ArrayList<>(parentIdList));
        parentMenuList.forEach(sysMenu -> {
            SysMenuRes sysMenuRes = new SysMenuRes(sysMenu);
            List<SysMenu> childrenList = new ArrayList<>();
            sysMenuList.forEach(childMenu -> {
                if (childMenu.getParentId().equals(sysMenu.getId())) {
                    childrenList.add(childMenu);
                }
            });
            sysMenuRes.setChildren(childrenList);
            sysMenuResList.add(sysMenuRes);
        });
        return sysMenuResList;
    }
}
