package com.linkpets.cms.aprilfool.service;

import com.linkpets.core.model.CmsActivityAssistance;
import com.linkpets.core.model.User;

import java.util.List;

public interface IActivityAssistService {

    String createActivityAssistRecord(String activityId,String userId,int assistType,String assistUserId);

    CmsActivityAssistance getActivityAssistRecordByUserId(String activityId,String userId);

    List<User> getActivityAssistFollowersByAssistUserId(String activityId,String assistUserId);

    int getActivityAssistNo(String activityId);
}
