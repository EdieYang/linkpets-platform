package com.linkpets.cms.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.manage.service.ISysUserService;
import com.linkpets.core.dao.*;
import com.linkpets.core.model.CmsAdoptOrgUser;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.SysUser;
import com.linkpets.core.model.SysUserRoleRel;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private CmsUserMapper cmsUserMapper;
    @Resource
    private CmsAdoptOrgUserMapper orgUserMapper;
    @Resource
    private SysUserRoleRelMapper sysUserRoleRelMapper;
    @Resource
    private SysRoleMapper roleMapper;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtOrgSysUser(String userId, String orgId) {
        String roleId = roleMapper.getRoleIdByRoleCode("ORG_STAFF");
        if (StringUtils.isEmpty(roleId)) {
            return null;
        }
        CmsUser user = cmsUserMapper.selectByPrimaryKey(userId);
        String mobilePhone = user.getMobilePhone();
        if (StringUtils.isEmpty(mobilePhone)) {
            return null;
        }
        SysUser sysUser = sysUserMapper.getSysUserByUserAccount(mobilePhone);
        if (sysUser != null) {
            return null;
        }
        //使用手机号创建账号
        SysUser newSysUser = new SysUser();
        String sysUserId = UUIDUtils.getId();
        newSysUser.setUserId(sysUserId);
        newSysUser.setUserAccount(mobilePhone);
        newSysUser.setUserName(user.getNickName());
        newSysUser.setMobilePhone(mobilePhone);
        newSysUser.setIsActive("1");
        newSysUser.setCreateDate(new Date());
        newSysUser.setCreateBy("SYSTEM");
        sysUserMapper.insertSelective(newSysUser);
        //绑定组织用户关系
        CmsAdoptOrgUser orgUser = new CmsAdoptOrgUser();
        orgUser.setId(UUIDUtils.getId());
        orgUser.setSysUserId(sysUserId);
        orgUser.setOrgId(orgId);
        orgUser.setUserId(userId);
        orgUser.setCreateDate(new Date());
        orgUserMapper.insertSelective(orgUser);
        //设置组织用户普通角色
        SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
        sysUserRoleRel.setId(UUIDUtils.getId());
        sysUserRoleRel.setUserId(sysUserId);
        sysUserRoleRel.setRoleId(roleId);
        sysUserRoleRel.setCreateDate(new Date());
        sysUserRoleRelMapper.insertSelective(sysUserRoleRel);
        return sysUserId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delOrgSysUser(String userId, String orgId) {
        CmsUser user = cmsUserMapper.selectByPrimaryKey(userId);
        String mobilePhone = user.getMobilePhone();
        SysUser sysUser = sysUserMapper.getSysUserByUserAccount(mobilePhone);
        String sysUserId = sysUser.getUserId();
        sysUserMapper.deleteByPrimaryKey(sysUserId);
        CmsAdoptOrgUser orgUser = orgUserMapper.selectByUserIdAndOrgIdAndSysUserId(userId, sysUserId, orgId);
        if (orgUser != null) {
            orgUserMapper.deleteByPrimaryKey(orgUser.getId());
        }
        sysUserRoleRelMapper.deleteByUserId(sysUserId);
    }


}
