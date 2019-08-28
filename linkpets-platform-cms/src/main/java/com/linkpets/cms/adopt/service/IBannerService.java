package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsBanner;

import java.util.List;

public interface IBannerService {

    /**
     * 获取banner列表
     *
     * @param activityId
     * @return
     */
    List<CmsBanner> getBannerList(String activityId);
}
