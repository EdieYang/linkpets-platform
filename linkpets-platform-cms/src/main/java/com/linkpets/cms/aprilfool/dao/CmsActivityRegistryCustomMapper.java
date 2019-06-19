package com.linkpets.cms.aprilfool.dao;

import com.linkpets.cms.aprilfool.model.CmsActivityCustomRegistry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsActivityRegistryCustomMapper {

    CmsActivityCustomRegistry getRegistryInfo(String registryId);

    List<CmsActivityCustomRegistry> getRegistryInfoByUserId(@Param("activityId") String activityId, @Param("userId") String userId);

    List<CmsActivityCustomRegistry> getRegistryInfoList(String activityId);

}
