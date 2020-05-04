package com.linkpets.cms.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.core.dao.CmsUserFollowMapper;
import com.linkpets.core.dao.CmsUserLoginMapper;
import com.linkpets.core.dao.CmsUserMapper;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.CmsUserFollow;
import com.linkpets.core.model.CmsUserLogin;
import com.linkpets.core.respEntity.RespOrgUser;
import com.linkpets.util.UUIDUtils;
import com.linkpets.util.UserAnalyseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {


    @Resource
    private CmsUserMapper cmsUserMapper;

    @Resource
    private CmsUserLoginMapper cmsUserLoginMapper;

    @Resource
    CmsUserFollowMapper cmsUserFollowMapper;


    @Override
    public PageInfo<CmsUser> getUserPage(String wxAccount, String mobilePhone, Integer authenticated, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsUser> userList = cmsUserMapper.getUserList(wxAccount, mobilePhone, authenticated);
        return new PageInfo<>(userList);
    }

    @Override
    public List<CmsUser> getUserList(String wxAccount, String mobilePhone, Integer authenticated) {
        return cmsUserMapper.getUserList(wxAccount, mobilePhone, authenticated);
    }

    @Override
    public CmsUser getUserInfoByUserId(String userId) {
        return cmsUserMapper.getUserInfoByUserId(userId);
    }

    @Override
    public CmsUser getUser(String userId) {
        return cmsUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUserInfo(CmsUser user) {
        cmsUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public String getLastLoginTime(String userId) {
        CmsUserLogin cmsUserLogin = cmsUserLoginMapper.getLastLoginTime(userId);
        if (cmsUserLogin == null) {
            return "";
        }
        return UserAnalyseUtil.getLastLoginTime(cmsUserLogin.getLoginDate());
    }

    @Override
    public int getLoginCount(String syncDate) {
        return cmsUserLoginMapper.getLoginCount(syncDate);
    }

    @Override
    public JSONObject getUserListFollow(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CmsUser> list = cmsUserMapper.getUserFollowList(param);
        PageInfo<CmsUser> page = new PageInfo<>(list);
        result.put("page", page.getPageNum());
        result.put("total", page.getTotal());
        result.put("rows", list);
        return result;
    }

    @Override
    public JSONObject getUserListFollowBy(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CmsUser> list = cmsUserMapper.getUserFollowByList(param);
        PageInfo<CmsUser> page = new PageInfo<>(list);
        result.put("page", page.getPageNum());
        result.put("total", page.getTotal());
        result.put("rows", list);
        return result;
    }

    @Override
    public int getFollowStatus(String userId, String targetUserId) {
        return cmsUserMapper.getFollowStatus(userId, targetUserId);
    }

    @Override
    public void crtFollow(String userId, String followBy) {
        CmsUserFollow follow = new CmsUserFollow();
        follow.setId(UUIDUtils.getId());
        follow.setCreateDate(new Date());
        follow.setUserId(userId);
        follow.setFollowBy(followBy);
        cmsUserFollowMapper.insertSelective(follow);
    }

    @Override
    public void delFollow(String userId, String followBy) {
        cmsUserFollowMapper.delFollow(userId, followBy);
    }

    @Override
    public Map<String, Object> getUserAddition(String userId) {
        return cmsUserMapper.getUserAddition(userId);
    }

    @Override
    public int getTotalUserCount() {
        return cmsUserMapper.getTotalUserCount();
    }

    @Override
    public PageInfo<RespOrgUser> getOrgUserInfoPage(String wxAccount, String mobilePhone, String orgId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespOrgUser> orgUserList = cmsUserMapper.getOrgUserInfoPage(wxAccount, mobilePhone, orgId);
        return new PageInfo<>(orgUserList);
    }

    @Override
    public List<RespOrgUser> getOrgUserInfoList(String orgId) {
        return cmsUserMapper.getOrgUserInfoList(orgId);
    }


}
