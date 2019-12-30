package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IBannerService;
import com.linkpets.core.dao.CmsBannerMapper;
import com.linkpets.core.model.CmsBanner;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class BannerServiceImpl implements IBannerService {


    @Resource
    private CmsBannerMapper bannerMapper;

    @Override
    public PageInfo<CmsBanner> getBannerPage(String activityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsBanner> cmsBannerList = bannerMapper.getBannerList(activityId, null);
        PageInfo<CmsBanner> pageInfo = new PageInfo<>(cmsBannerList);
        return pageInfo;
    }

    @Override
    public List<CmsBanner> getBannerList(String activityId, Integer count) {
        return bannerMapper.getBannerList(activityId, count);
    }

    @Override
    public String crtBanner(CmsBanner cmsBanner) {
        String id = UUIDUtils.getId();
        cmsBanner.setBannerId(id);
        cmsBanner.setCreateTime(new Date());
        bannerMapper.insertSelective(cmsBanner);
        return id;
    }

    @Override
    public void uptBanner(CmsBanner cmsBanner) {
        bannerMapper.updateByPrimaryKeySelective(cmsBanner);
    }
}
