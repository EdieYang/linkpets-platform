package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IGroupActivityService;
import com.linkpets.core.dao.CmsGroupActivityMapper;
import com.linkpets.core.dao.CmsGroupActivityUserRelMapper;
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
    private CmsGroupActivityMapper cmsGroupActivityMapper;

    @Resource
    private CmsGroupActivityUserRelMapper cmsGroupActivityUserRelMapper;

    @Override
    public List<CmsGroupActivity> getGroupActivityList(Integer activityType, Integer isActive) {
        return cmsGroupActivityMapper.getGroupActivityList(activityType, isActive);
    }

    @Override
    public PageInfo<RespGroupActivity> getGroupActivityPage(Integer activityType, Integer isActive, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupActivity> groupActivityList = new ArrayList<>();
        List<CmsGroupActivity> cmsGroupActivityList = cmsGroupActivityMapper.getGroupActivityList(activityType, isActive);
        cmsGroupActivityList.forEach(cmsGroupActivity -> {
            RespGroupActivity groupActivity = new RespGroupActivity();
            BeanUtils.copyProperties(cmsGroupActivity, groupActivity);
            groupActivity.setActivityStatus(calActivityStatus(cmsGroupActivity));
            groupActivityList.add(groupActivity);
        });
        PageInfo<RespGroupActivity> cmsGroupActivityPageInfo = new PageInfo<>(groupActivityList);
        return cmsGroupActivityPageInfo;
    }

    @Override
    public List<RespGroupActivity> getGroupActivityListByUserId(String userId) {
        return cmsGroupActivityMapper.getGroupActivityListByUserId(userId);
    }

    @Override
    public CmsGroupActivity getGroupActivityInfo(String activityId) {
        CmsGroupActivity groupActivity = cmsGroupActivityMapper.selectByPrimaryKey(activityId);
        groupActivity.setActivityStatus(calActivityStatus(groupActivity));
        return groupActivity;
    }

    @Override
    public CmsGroupActivity getGroupActivityInfoWithUserId(String activityId, String userId) {
        return cmsGroupActivityMapper.getGroupActivityInfoWithUserId(activityId, userId);
    }

    @Override
    public String crtGroupActivity(CmsGroupActivity cmsGroupActivity) {
        String activityId = UUIDUtils.getId();
        cmsGroupActivity.setId(activityId);
        cmsGroupActivity.setCreateDate(new Date());
        cmsGroupActivityMapper.insertSelective(cmsGroupActivity);
        return activityId;
    }

    @Override
    public void uptGroupActivity(CmsGroupActivity cmsGroupActivity) {
        cmsGroupActivityMapper.updateByPrimaryKeySelective(cmsGroupActivity);
    }

    @Override
    public void delGroupActivity(String activityId) {
        cmsGroupActivityMapper.delGroupActivity(activityId);
    }

    @Override
    public String crtGroupActivityFollow(String userId, String activityId) {
        CmsGroupActivityUserRel followUser = cmsGroupActivityUserRelMapper.getFollowUserByUserIdAndActivityId(userId, activityId);
        if (followUser == null) {
            String id = UUIDUtils.getId();
            followUser = new CmsGroupActivityUserRel();
            followUser.setId(id);
            followUser.setUserId(userId);
            followUser.setActivityId(activityId);
            followUser.setCreateDate(new Date());
            cmsGroupActivityUserRelMapper.insertSelective(followUser);
            return id;
        }
        followUser.setIsValid(1);
        cmsGroupActivityUserRelMapper.updateByPrimaryKeySelective(followUser);
        return followUser.getId();
    }

    @Override
    public void delGroupActivityFollow(String userId, String activityId) {
        CmsGroupActivityUserRel followUser = cmsGroupActivityUserRelMapper.getFollowUserByUserIdAndActivityId(userId, activityId);
        if (followUser != null) {
            followUser.setIsValid(0);
            cmsGroupActivityUserRelMapper.updateByPrimaryKeySelective(followUser);
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
