package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.service.IBannerService;
import com.linkpets.core.dao.CmsBannerMapper;
import com.linkpets.core.model.CmsBanner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BannerServiceImpl implements IBannerService {


    @Resource
    private CmsBannerMapper bannerMapper;

    @Override
    public List<CmsBanner> getBannerList(String activityId) {
        return bannerMapper.getBannerList(activityId);
    }
}
