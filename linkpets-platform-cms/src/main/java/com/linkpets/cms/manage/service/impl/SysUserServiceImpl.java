package com.linkpets.cms.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.manage.service.ISysUserService;
import com.linkpets.core.dao.SysUserMapper;
import com.linkpets.core.model.SysUser;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public PageInfo<SysUser> getSysUserPage(String userAccount, String userName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = sysUserMapper.getSysUserList(userAccount, userName);
        return new PageInfo<>(sysUserList);
    }

    @Override
    public SysUser getSysUser(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser getSysUserByUserAccount(String userAcc) {
        return sysUserMapper.getSysUserByUserAccount(userAcc);
    }

    @Override
    public SysUser getSysUserByUserId(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public String crtSysUser(SysUser sysUser) {
        String userId = UUIDUtils.getId();
        sysUser.setCreateDate(new Date());
        sysUser.setUserId(userId);
        sysUserMapper.insertSelective(sysUser);
        return userId;
    }

    @Override
    public void uptSysUser(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public void delSysUser(String userId) {
        sysUserMapper.delSysUser(userId);
    }

    @Override
    public void batchDelSysUser(List<String> userIdList) {
        sysUserMapper.batchDelSysUser(userIdList);
    }


}
