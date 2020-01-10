package com.linkpets.cms.group.service;

import com.linkpets.cms.group.enums.PointsChannelEnum;
import com.linkpets.core.model.CmsPointStatement;

import java.util.List;

public interface IPointStatementService {

    void crtPointStatement(String userId, int points, String targetId, PointsChannelEnum pointsChannelEnum);

    Integer crtPointStatementByChannel(String userId, String targetId, int channel,int rewardPoints);

    List<CmsPointStatement> getPointStatementList(String userId);

    List<CmsPointStatement> getPointStatementListByChannel(String userId, int channel);

    Integer getUserPoints(String userId);

}
