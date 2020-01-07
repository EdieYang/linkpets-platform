package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptPointsStatement;

import java.util.List;

public interface CmsAdoptPointsStatementMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsAdoptPointsStatement record);

    int insertSelective(CmsAdoptPointsStatement record);

    CmsAdoptPointsStatement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsAdoptPointsStatement record);

    int updateByPrimaryKey(CmsAdoptPointsStatement record);

    List<CmsAdoptPointsStatement> getPointStatementList(String userId);

    List<CmsAdoptPointsStatement> getPointStatementListByChannel(String userId, String channel);

    List<CmsAdoptPointsStatement> getPointStatementListByChannelAndDay(String userId, String channel, String date);

    List<CmsAdoptPointsStatement> getPointStatementListByTargetId(String userId, String channel, String date, String targetId);

    Integer getUserPoints(String userId);
}