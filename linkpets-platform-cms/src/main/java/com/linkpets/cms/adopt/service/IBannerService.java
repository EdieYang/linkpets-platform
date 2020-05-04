package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsBanner;

import java.util.List;

public interface IBannerService {


    /**
     * 分页查询banner列表
     *
     * @param type
     * @param position
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CmsBanner> getBannerPage(Integer type, Integer position, Integer pageNum, Integer pageSize);

    /**
     * 获取banner列表
     *
     * @param type
     * @param position
     * @return
     */
    List<CmsBanner> getBannerList(Integer type, Integer position);

    /**
     * 创建Banner
     *
     * @param cmsBanner
     * @return
     */
    String crtBanner(CmsBanner cmsBanner);

    /**
     * 更新Banner
     *
     * @param cmsBanner
     */
    void uptBanner(CmsBanner cmsBanner);

    /**
     * 删除Banner
     *
     * @param bannerId
     */
    void delBanner(String bannerId);
}
