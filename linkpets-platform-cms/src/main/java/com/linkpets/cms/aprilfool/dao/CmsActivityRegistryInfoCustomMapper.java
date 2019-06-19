package com.linkpets.cms.aprilfool.dao;

import com.linkpets.core.model.CmsActivityRegistryInfo;
import org.apache.ibatis.annotations.Param;

public interface CmsActivityRegistryInfoCustomMapper {

    CmsActivityRegistryInfo selectRegistryInfoByUserIdAndActivityId(@Param("userId") String userId, @Param("activityId") String activityId);
}
