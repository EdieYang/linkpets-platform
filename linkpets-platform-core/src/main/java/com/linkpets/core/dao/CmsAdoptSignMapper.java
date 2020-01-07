package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptSign;

import java.util.List;

public interface CmsAdoptSignMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsAdoptSign record);

    int insertSelective(CmsAdoptSign record);

    CmsAdoptSign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsAdoptSign record);

    int updateByPrimaryKey(CmsAdoptSign record);

    CmsAdoptSign getSignRecordByDate(String userId, String date);

    List<CmsAdoptSign> getSignRecordList(String userId);
}