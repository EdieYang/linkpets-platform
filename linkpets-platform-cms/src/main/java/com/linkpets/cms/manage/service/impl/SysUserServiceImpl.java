package com.linkpets.cms.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.manage.dao.SysUserCustomMapper;
import com.linkpets.cms.manage.model.SysUserCustom;
import com.linkpets.cms.manage.service.ISysUserService;
import com.linkpets.core.dao.SysUserMapper;
import com.linkpets.core.model.SysUser;
import com.linkpets.util.ResponseCode;
import com.linkpets.util.ResponseCodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/15
 */

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserCustomMapper sysUserCustomMapper;


    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public JSONObject listSysUserCustom(String orgId, String chainId, int pageNo, int pageSize) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNo, pageSize);
        List<SysUserCustom> sysUserCustomList = sysUserCustomMapper.listSysUserCustom(orgId, chainId);
        PageInfo<SysUserCustom> page = new PageInfo<>(sysUserCustomList);
        result.put("page", page.getPageNum());
        result.put("records", page.getTotal());
        result.put("rows", sysUserCustomList);
        return result;
    }

    @Override
    public JSONObject loginSysUser(String userName, String password) {
        JSONObject result = new JSONObject();
        SysUser sysUser = sysUserCustomMapper.getSysUserByUserName(userName);
        if (sysUser == null) {
            return new ResponseCodeFactory(ResponseCode.INVALID_ACCOUNT).getResponseCode();
        }

        if (sysUser.getIsActive() == 0) {
            return new ResponseCodeFactory(ResponseCode.PASSIVE_ACCOUNT).getResponseCode();
        }

        if (!password.equals(sysUser.getPassword())) {
            return new ResponseCodeFactory(ResponseCode.PASSWORD_WRONG).getResponseCode();
        }

        result.put("data", sysUser);
        return result;
    }

    @Override
    public SysUser getSysUser(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser updateSysUser(String userId, String chainId) {
        SysUser sysUser=sysUserMapper.selectByPrimaryKey(userId);
        sysUser.setChainId(chainId);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return sysUser;
    }
}
