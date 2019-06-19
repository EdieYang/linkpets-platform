package com.linkpets.cms.aprilfool.dao;

import com.linkpets.core.model.CmsActivityAssistance;
import com.linkpets.core.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsActivityAssistanceCustomMapper {

    CmsActivityAssistance getActivityAssistanceByUserId(@Param("activityId") String activityId, @Param("userId") String userId);

    CmsActivityAssistance getMaxOrderOfActivityAssistanceByActivityId(@Param("activityId") String activityId);

    List<User> getActivityAssistFollowersByAssistUserId(@Param("activityId") String activityId, @Param("assistUserId") String assistUserId);

    int getActivityAssistNo(String activityId);
}
