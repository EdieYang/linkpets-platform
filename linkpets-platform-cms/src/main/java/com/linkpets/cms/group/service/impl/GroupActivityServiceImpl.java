package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IGroupActivityService;
import com.linkpets.core.dao.CmsAdoptGroupActivityMapper;
import com.linkpets.core.dao.CmsAdoptGroupActivityUserRelMapper;
import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.model.CmsGroupActivityUserRel;
import com.linkpets.core.respEntity.RespGroupActivity;
import com.linkpets.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<CmsGroupActivity> getAdoptGroupActivityList(Integer activityType, Integer isActive) {
        return cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(activityType, isActive);
    }

    @Override
    public PageInfo<RespGroupActivity> getAdoptGroupActivityPage(Integer activityType, Integer isActive, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupActivity> groupActivityList = new ArrayList<>();
        List<CmsGroupActivity> cmsAdoptGroupActivityList = cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(activityType, isActive);
        cmsAdoptGroupActivityList.forEach(cmsGroupActivity -> {
            RespGroupActivity groupActivity = new RespGroupActivity();
            BeanUtils.copyProperties(cmsGroupActivity, groupActivity);
            groupActivity.setActivityStatus(calActivityStatus(cmsGroupActivity));
            groupActivityList.add(groupActivity);
        });
        PageInfo<RespGroupActivity> cmsAdoptGroupActivityPageInfo = new PageInfo<>(groupActivityList);
        return cmsAdoptGroupActivityPageInfo;
    }

    @Override
    public List<RespGroupActivity> getGroupActivityListByUserId(String userId) {
        return cmsAdoptGroupActivityMapper.getGroupActivityListByUserId(userId);
    }

    @Override
    public CmsGroupActivity getAdoptGroupActivityInfo(String activityId) {
        return cmsAdoptGroupActivityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public String crtAdoptGroupActivity(CmsGroupActivity cmsGroupActivity) {
        String activityId = UUIDUtils.getId();
        cmsGroupActivity.setId(activityId);
        cmsGroupActivity.setCreateDate(new Date());
        cmsAdoptGroupActivityMapper.insertSelective(cmsGroupActivity);
        return activityId;
    }

    @Override
    public void uptAdoptGroupActivity(CmsGroupActivity cmsGroupActivity) {
        cmsAdoptGroupActivityMapper.updateByPrimaryKeySelective(cmsGroupActivity);
    }

    @Override
    public void delGroupActivity(String activityId) {
        cmsAdoptGroupActivityMapper.delGroupActivity(activityId);
    }

    @Override
    public String crtGroupActivityFollow(String userId, String activityId) {
        CmsGroupActivityUserRel followUser = cmsAdoptGroupActivityUserRelMapper.getFollowUserByUserIdAndActivityId(userId, activityId);
        if (followUser == null) {
            String id = UUIDUtils.getId();
            followUser = new CmsGroupActivityUserRel();
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
        CmsGroupActivityUserRel followUser = cmsAdoptGroupActivityUserRelMapper.getFollowUserByUserIdAndActivityId(userId, activityId);
        if (followUser != null) {
            followUser.setIsValid(0);
            cmsAdoptGroupActivityUserRelMapper.updateByPrimaryKeySelective(followUser);
        }
    }

    private String calActivityStatus(CmsGroupActivity cmsGroupActivity) {
        Date registerStartTime = cmsGroupActivity.getActivityRegisterStartTime();
        Date registerEndTime = cmsGroupActivity.getActivityRegisterEndTime();
        Date activityStartTime = cmsGroupActivity.getActivityStartTime();
        Date activityEndTime = cmsGroupActivity.getActivityEndTime();
        Integer isActive = cmsGroupActivity.getIsActive();
        Date now = new Date();
        if (isActive == 0) {
            return "活动已下线";
        }
        if (now.after(registerStartTime) && now.before(registerEndTime)) {
            return "活动报名中";
        }
        if (now.before(registerStartTime) || now.before(activityStartTime) && now.after(registerEndTime)) {
            return "活动即将开始";
        }
        if (now.after(activityStartTime) && now.before(activityEndTime)) {
            return "活动进行中";
        }
        if (now.after(activityEndTime)) {
            return "活动已结束";
        }
        return "";
    }
}
