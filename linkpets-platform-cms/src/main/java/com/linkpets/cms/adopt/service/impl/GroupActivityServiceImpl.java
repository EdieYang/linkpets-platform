package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IGroupActivityService;
import com.linkpets.core.dao.CmsAdoptGroupActivityMapper;
import com.linkpets.core.dao.CmsAdoptGroupActivityUserRelMapper;
import com.linkpets.core.model.CmsAdoptGroupActivity;
import com.linkpets.core.model.CmsAdoptGroupActivityUserRel;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Edie
 */
@Service
public class GroupActivityServiceImpl implements IGroupActivityService {

    @Resource
    private CmsAdoptGroupActivityMapper cmsAdoptGroupActivityMapper;

    @Resource
    private CmsAdoptGroupActivityUserRelMapper cmsAdoptGroupActivityUserRelMapper;

    @Override
    public List<CmsAdoptGroupActivity> getAdoptGroupActivityList(String groupId, Integer isActive) {
        return cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(groupId, isActive);
    }

    @Override
    public PageInfo<CmsAdoptGroupActivity> getAdoptGroupActivityPage(String groupId, Integer isActive, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptGroupActivity> cmsAdoptGroupActivityList = cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(groupId, isActive);
        PageInfo<CmsAdoptGroupActivity> cmsAdoptGroupActivityPageInfo = new PageInfo<>(cmsAdoptGroupActivityList);
        return cmsAdoptGroupActivityPageInfo;
    }

    @Override
    public CmsAdoptGroupActivity getAdoptGroupActivityInfo(String activityId) {
        return cmsAdoptGroupActivityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public String crtAdoptGroupActivity(CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        String activityId = UUIDUtils.getUUID();
        cmsAdoptGroupActivity.setId(activityId);
        cmsAdoptGroupActivity.setCreateDate(new Date());
        cmsAdoptGroupActivityMapper.insertSelective(cmsAdoptGroupActivity);
        return activityId;
    }

    @Override
    public void uptAdoptGroupActivity(CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        cmsAdoptGroupActivityMapper.updateByPrimaryKeySelective(cmsAdoptGroupActivity);
    }

    @Override
    public String crtGroupActivityFollow(String userId, String activityId) {
        CmsAdoptGroupActivityUserRel followUser = cmsAdoptGroupActivityUserRelMapper.getFollowUserByUserIdAndActivityId(userId, activityId);
        if (followUser == null) {
            String id = UUIDUtils.getUUID();
            followUser = new CmsAdoptGroupActivityUserRel();
            followUser.setId(id);
            followUser.setUserId(userId);
            followUser.setActivityId(activityId);
            cmsAdoptGroupActivityUserRelMapper.insertSelective(followUser);
            return id;
        }
        followUser.setIsValid(1);
        cmsAdoptGroupActivityUserRelMapper.updateByPrimaryKeySelective(followUser);
        return followUser.getId();
    }

    @Override
    public void delGroupActivityFollow(String userId, String activityId) {
        CmsAdoptGroupActivityUserRel followUser = cmsAdoptGroupActivityUserRelMapper.getFollowUserByUserIdAndActivityId(userId, activityId);
        if (followUser != null) {
            followUser.setIsValid(0);
            cmsAdoptGroupActivityUserRelMapper.updateByPrimaryKeySelective(followUser);
        }
    }
}
