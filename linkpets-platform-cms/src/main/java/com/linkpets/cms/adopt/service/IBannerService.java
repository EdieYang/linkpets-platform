package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsBanner;

import java.util.List;

public interface IBannerService {

    /**
     * 分页查询banner列表
     *
     * @param activityId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CmsBanner> getBannerPage(String activityId, Integer pageNum, Integer pageSize);

    /**
     * 获取banner列表
     *
     * @param activityId
     * @return
     */
    List<CmsBanner> getBannerList(String activityId, Integer count);

    /**
     * 创建Banner
     *
     * @param cmsBanner
     * @return
     */
    String crtBanner(CmsBanner cmsBanner);

    /**
     * 更新Banner
     * @param cmsBanner
     */
    void uptBanner(CmsBanner cmsBanner);
}
