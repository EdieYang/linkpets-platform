package com.linkpets.cms.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.manage.service.ISysPermissionService;
import com.linkpets.core.dao.SysPermissionMapper;
import com.linkpets.core.model.SysPermission;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Edie
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public PageInfo<SysPermission> getSysPermissionPage(String permissionName, String permissionCode, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysPermission> sysPermissionList = sysPermissionMapper.getSysPermissionList(permissionName, permissionCode);
        return new PageInfo<>(sysPermissionList);
    }

    @Override
    public SysPermission getSysPermission(String id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public String crtSysPermission(SysPermission sysPermission) {
        String id = UUIDUtils.getId();
        sysPermission.setId(id);
        sysPermission.setCreateDate(new Date());
        sysPermissionMapper.insertSelective(sysPermission);
        return id;
    }

    @Override
    public void uptSysPermission(SysPermission sysPermission) {
        sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
    }

    @Override
    public void delSysPermission(String id) {
        sysPermissionMapper.delSysPermission(id);
    }

    @Override
    public void batchDelSysPermission(List<String> idList) {
        sysPermissionMapper.batchDelSysPermission(idList);
    }

    @Override
    public List<SysPermission> getSysPermissionListByUserId(String userId) {
        return sysPermissionMapper.getSysPermissionListByUserId(userId);
    }
}
