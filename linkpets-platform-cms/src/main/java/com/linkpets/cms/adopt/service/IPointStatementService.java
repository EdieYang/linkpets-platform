package com.linkpets.cms.adopt.service;

import com.linkpets.cms.adopt.enums.PointsChannelEnum;
import com.linkpets.core.model.CmsAdoptPointsStatement;

import java.util.List;

public interface IPointStatementService {

    void crtPointStatement(String userId, int points, String targetId, PointsChannelEnum pointsChannelEnum);

    Integer crtPointStatementByChannel(String userId, String targetId, String channel);

    List<CmsAdoptPointsStatement> getPointStatementList(String userId);

    List<CmsAdoptPointsStatement> getPointStatementListByChannel(String userId, String channel);

    Integer getUserPoints(String userId);

}
