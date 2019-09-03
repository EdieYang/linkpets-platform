package com.linkpets.cms.aprilfool.service;

import com.linkpets.core.model.CmsActivityAssistance;

public interface IActivityAssistService {

    String createActivityAssistRecord(String activityId,String userId,int assistType,String assistUserId);

    CmsActivityAssistance getActivityAssistRecordByUserId(String activityId,String userId);

    int getActivityAssistNo(String activityId);
}
