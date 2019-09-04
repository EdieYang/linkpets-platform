package com.linkpets.cms.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
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

/**
 * @author SteveYang
 * @date 2019/3/15
 */

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public JSONObject listSysUser(String orgId, String chainId, int pageNo, int pageSize) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNo, pageSize);
        List<SysUser> sysUserCustomList = sysUserMapper.listSysUser(orgId, chainId);
        PageInfo<SysUser> page = new PageInfo<>(sysUserCustomList);
        result.put("page", page.getPageNum());
        result.put("records", page.getTotal());
        result.put("rows", sysUserCustomList);
        return result;
    }

    @Override
    public JSONObject loginSysUser(String userName, String password) {
        JSONObject result = new JSONObject();
        SysUser sysUser = sysUserMapper.selectByUserName(userName);


        result.put("data", sysUser);
        return result;
    }

    @Override
    public SysUser getSysUserByUserName(String userName) {
        return sysUserMapper.selectByUserName(userName);
    }

    @Override
    public SysUser getSysUser(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser updateSysUser(String userId, String chainId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        sysUser.setChainId(chainId);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return sysUser;
    }

    @Override
    public void register(String userName, String password) {
        SysUser user = new SysUser();
        user.setUserId(UUIDUtils.getUUID());
        user.setUserName(userName);
        user.setPassword(password);
        user.setRoleId("10001");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        sysUserMapper.insertSelective(user);
    }
}
