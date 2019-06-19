package com.linkpets.cms.aprilfool.dao;

import com.linkpets.core.model.CmsActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsActivityCustomMapper {

    List<CmsActivity> getActivitiesList(@Param("orgId") String orgId, @Param("startTime") String startTime,
                                        @Param("endTime") String endTime, @Param("isActive") int isActive);

}
