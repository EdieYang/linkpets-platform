package com.linkpets.cms.aprilfool.service.impl;


import com.linkpets.cms.aprilfool.service.IActivityService;
import com.linkpets.core.dao.CmsActivityMapper;
import com.linkpets.core.model.CmsActivity;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.linkpets.util.DateUtils.getFormatDateTime;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Resource
    private CmsActivityMapper cmsActivityMapper;


    @Override
    public String createActivity(String activityName, String introduction, String startTime, String endTime, String orgId) {
        String activityId = UUIDUtils.getUUID();
        CmsActivity activity = new CmsActivity(activityId, activityName, introduction, startTime, endTime, orgId, new Date());
        cmsActivityMapper.insertSelective(activity);
        return activity.getActivityId();
    }

    @Override
    public CmsActivity modifyActivity(String activityId, String activityName, String introduction, String startTime, String endTime, String orgId, int isActive) {
        CmsActivity activity = cmsActivityMapper.selectByPrimaryKey(activityId);
        if (StringUtils.isNotEmpty(activityName)) {
            activity.setActivityName(activityName);
        }

        if (StringUtils.isNotEmpty(introduction)) {
            activity.setIntroduction(introduction);
        }

        if (StringUtils.isNotEmpty(startTime)) {
            activity.setStartTime(getFormatDateTime(startTime));
        }

        if (StringUtils.isNotEmpty(endTime)) {
            activity.setEndTime(getFormatDateTime(endTime));
        }

        if (StringUtils.isNotEmpty(orgId)) {
            activity.setOrgId(orgId);
        }

        if (isActive != -1) {
            activity.setIsActive(isActive);
        }

        cmsActivityMapper.updateByPrimaryKeySelective(activity);

        return activity;
    }

    @Override
    public void deleteActivity(String activityId) {
        CmsActivity cmsActivity = new CmsActivity();
        cmsActivity.setActivityId(activityId);
        cmsActivity.setIsValid(0);
        cmsActivityMapper.updateByPrimaryKeySelective(cmsActivity);
    }

    @Override
    public CmsActivity getActivity(String activityId) {
        CmsActivity cmsActivity = cmsActivityMapper.selectByPrimaryKey(activityId);
        return cmsActivity;
    }

    @Override
    public List<CmsActivity> getActivitiesList(String orgId, String startTime, String endTime, int isActive) {
        List<CmsActivity> cmsActivity = cmsActivityMapper.getActivitiesList(orgId, startTime, endTime, isActive);
        return cmsActivity;
    }
}
