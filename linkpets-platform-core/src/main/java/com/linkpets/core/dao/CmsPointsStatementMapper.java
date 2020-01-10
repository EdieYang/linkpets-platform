package com.linkpets.core.dao;

import com.linkpets.core.model.CmsPointStatement;

import java.util.List;

public interface CmsPointsStatementMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsPointStatement record);

    int insertSelective(CmsPointStatement record);

    CmsPointStatement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsPointStatement record);

    int updateByPrimaryKey(CmsPointStatement record);

    List<CmsPointStatement> getPointStatementList(String userId);

    List<CmsPointStatement> getPointStatementListByTargetIdAndUserId(String userId, String targetId,int channel);

    List<CmsPointStatement> getPointStatementListByChannel(String userId, int channel);

    List<CmsPointStatement> getPointStatementListByChannelAndDay(String userId, int channel, String date);

    List<CmsPointStatement> getPointStatementListByTargetId(String userId, int channel, String date, String targetId);

    Integer getUserPoints(String userId);
}