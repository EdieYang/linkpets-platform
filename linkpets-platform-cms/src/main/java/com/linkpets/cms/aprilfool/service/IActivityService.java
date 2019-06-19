package com.linkpets.cms.aprilfool.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsActivity;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/23
 */
public interface IActivityService {

    String createActivity(String activityName, String introduction, String startTime, String endTime, String orgId);


    CmsActivity modifyActivity(String activityId, String activityName, String introduction, String startTime, String endTime, String orgId, int isActive);

    void deleteActivity(String activityId);

    CmsActivity getActivity(String activityId);

    List<CmsActivity> getActivitiesList(String orgId, String startTime, String endTime, int isActive);
}
