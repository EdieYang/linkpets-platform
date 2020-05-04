package com.linkpets.core.dao;

import com.linkpets.core.model.CmsBanner;

import java.util.List;

public interface CmsBannerMapper {
    int deleteByPrimaryKey(String bannerId);

    int insert(CmsBanner record);

    int insertSelective(CmsBanner record);

    CmsBanner selectByPrimaryKey(String bannerId);

    int updateByPrimaryKeySelective(CmsBanner record);

    int updateByPrimaryKey(CmsBanner record);

    List<CmsBanner> getBannerList(Integer type, Integer position);
}