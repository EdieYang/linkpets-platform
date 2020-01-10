package com.linkpets.core.dao;

import com.linkpets.core.model.CmsUserSign;

import java.util.List;

public interface CmsUserSignMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsUserSign record);

    int insertSelective(CmsUserSign record);

    CmsUserSign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsUserSign record);

    int updateByPrimaryKey(CmsUserSign record);

    CmsUserSign getSignRecordByDate(String userId, String date);

    List<CmsUserSign> getSignRecordList(String userId);
}