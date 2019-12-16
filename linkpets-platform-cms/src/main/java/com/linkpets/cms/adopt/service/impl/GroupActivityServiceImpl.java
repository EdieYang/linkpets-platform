package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IGroupActivityService;
import com.linkpets.core.dao.CmsAdoptGroupActivityMapper;
import com.linkpets.core.dao.CmsAdoptGroupActivityUserRelMapper;
import com.linkpets.core.model.CmsAdoptGroupActivity;
import com.linkpets.core.model.CmsAdoptGroupActivityUserRel;
import com.linkpets.core.respEntity.RespGroupActivity;
import com.linkpets.util.DateUtils;
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
    public List<CmsAdoptGroupActivity> getAdoptGroupActivityList(Integer activityType, Integer isActive) {
        return cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(activityType, isActive);
    }

    @Override
    public PageInfo<RespGroupActivity> getAdoptGroupActivityPage(Integer activityType, Integer isActive, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupActivity> groupActivityList = new ArrayList<>();
        List<CmsAdoptGroupActivity> cmsAdoptGroupActivityList = cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(activityType, isActive);
        cmsAdoptGroupActivityList.forEach(cmsAdoptGroupActivity -> {
            RespGroupActivity groupActivity = new RespGroupActivity();
            BeanUtils.copyProperties(cmsAdoptGroupActivity, groupActivity);
            groupActivity.setActivityStatus(calActivityStatus(cmsAdoptGroupActivity));
            groupActivityList.add(groupActivity);
        });
        PageInfo<RespGroupActivity> cmsAdoptGroupActivityPageInfo = new PageInfo<>(groupActivityList);
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

    private String calActivityStatus(CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        Date registerStartTime = cmsAdoptGroupActivity.getActivityRegisterStartTime();
        Date registerEndTime = cmsAdoptGroupActivity.getActivityRegisterEndTime();
        Date activityStartTime = cmsAdoptGroupActivity.getActivityStartTime();
        Date activityEndTime = cmsAdoptGroupActivity.getActivityEndTime();
        Integer isActive = cmsAdoptGroupActivity.getIsActive();
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
