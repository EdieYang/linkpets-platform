package com.linkpets.cms.aprilfool.service;

import com.linkpets.core.model.CmsActivityDraft;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/26
 */
public interface IActivityDraftService {

    int getActivityDraftExistNoByUserId(String activityId, String userId);

    List<CmsActivityDraft> getActivityDraftListByUserId(String activityId, String userId);

    int getActivityDraftCount(String activityId, String userId);

    CmsActivityDraft doActivityDraftProcess(String activityId, String userId);

    CmsActivityDraft addActivityDraft(String activityId, String userId);
}
