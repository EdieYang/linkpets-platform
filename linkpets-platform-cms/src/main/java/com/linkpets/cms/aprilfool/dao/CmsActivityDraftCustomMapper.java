package com.linkpets.cms.aprilfool.dao;

import com.linkpets.cms.aprilfool.model.CmsActivityCustomDraft;
import com.linkpets.core.model.CmsActivityDraft;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/26
 */
public interface CmsActivityDraftCustomMapper {

    int getActivityDraftExistNoByUserId(@Param("activityId") String activityId, @Param("userId") String userId);

    int getActivityDraftCount(@Param("activityId") String activityId, @Param("userId") String userId);

    List<CmsActivityCustomDraft> getActivityCouponDraftListByUserId(@Param("activityId") String activityId, @Param("userId") String userId, @Param("presentType") String presentType);

    List<CmsActivityDraft> getActivityDraftListByUserId(@Param("activityId") String activityId, @Param("userId") String userId);

    CmsActivityCustomDraft getActivityCouponDraftByDraftId(@Param("draftId") String draftId);

    CmsActivityDraft getActivityUnDraftedRecordByUserId(@Param("activityId") String activityId, @Param("userId") String userId);
}
