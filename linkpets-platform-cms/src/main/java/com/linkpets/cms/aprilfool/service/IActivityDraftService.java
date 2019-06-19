package com.linkpets.cms.aprilfool.service;

import com.linkpets.cms.aprilfool.model.CmsActivityCustomDraft;
import com.linkpets.core.model.CmsActivity;
import com.linkpets.core.model.CmsActivityDraft;
import com.linkpets.enums.PresentType;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/26
 */
public interface IActivityDraftService {

    int getActivityDraftExistNoByUserId(String activityId, String userId);

    List<CmsActivityCustomDraft> getActivityDraftListByUserId(String activityId, String userId);

    int getActivityDraftCount(String activityId, String userId);

    CmsActivityCustomDraft doActivityDraftProcess(String activityId, String userId);

    CmsActivityDraft addActivityDraft(String activityId, String userId);
}
