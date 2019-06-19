package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsBanner;

import java.util.List;

public interface IBannerService {

    List<CmsBanner> getBannerList(String activityId);
}
