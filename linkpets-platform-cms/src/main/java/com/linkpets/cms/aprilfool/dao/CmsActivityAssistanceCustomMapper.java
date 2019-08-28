package com.linkpets.cms.aprilfool.dao;

import com.linkpets.core.model.CmsActivityAssistance;
import org.apache.ibatis.annotations.Param;



public interface CmsActivityAssistanceCustomMapper {

    CmsActivityAssistance getActivityAssistanceByUserId(@Param("activityId") String activityId, @Param("userId") String userId);

    CmsActivityAssistance getMaxOrderOfActivityAssistanceByActivityId(@Param("activityId") String activityId);

//    List<ActivityUser> getActivityAssistFollowersByAssistUserId(@Param("activityId") String activityId, @Param("assistUserId") String assistUserId);

    int getActivityAssistNo(String activityId);
}
