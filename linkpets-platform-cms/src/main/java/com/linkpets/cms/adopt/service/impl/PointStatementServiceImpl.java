package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.enums.PointsChannelEnum;
import com.linkpets.cms.adopt.service.IPointStatementService;
import com.linkpets.core.dao.CmsAdoptPointsStatementMapper;
import com.linkpets.core.model.CmsAdoptPointsStatement;
import com.linkpets.util.DateUtils;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PointStatementServiceImpl implements IPointStatementService {

    @Resource
    private CmsAdoptPointsStatementMapper pointsStatementMapper;

    @Override
    public void crtPointStatement(String userId, int points, String targetId, PointsChannelEnum pointsChannelEnum) {
        CmsAdoptPointsStatement pointsStatement = new CmsAdoptPointsStatement();
        pointsStatement.setId(UUIDUtils.getId());
        pointsStatement.setUserId(userId);
        pointsStatement.setTargetId(targetId);
        pointsStatement.setChannel(pointsChannelEnum.getChannel());
        pointsStatement.setPoints(points);
        pointsStatement.setCreateDate(new Date());
        pointsStatementMapper.insertSelective(pointsStatement);
    }

    @Override
    public Integer crtPointStatementByChannel(String userId, String targetId, String channel) {
        Integer points = 0;
        List<CmsAdoptPointsStatement> pointsStatementList = pointsStatementMapper.getPointStatementListByChannelAndDay(userId, channel, DateUtils.getCurrentDay());
        switch (channel) {
            case "4":
                List<CmsAdoptPointsStatement> sameSharePointStatement = pointsStatementMapper.getPointStatementListByTargetId(userId, channel, DateUtils.getCurrentDay(), targetId);
                if (sameSharePointStatement.size() == 0 && pointsStatementList != null && pointsStatementList.size() < 3) {
                    this.crtPointStatement(userId, PointsChannelEnum.ADOPT_INFO_SHARE.getPoints(), targetId, PointsChannelEnum.ADOPT_INFO_SHARE);
                    points = PointsChannelEnum.ADOPT_INFO_SHARE.getPoints();
                }
                break;
            case "5":
                List<CmsAdoptPointsStatement> sameBrowsePointStatement = pointsStatementMapper.getPointStatementListByTargetId(userId, channel, DateUtils.getCurrentDay(), targetId);
                if (sameBrowsePointStatement.size() == 0 && pointsStatementList != null && pointsStatementList.size() < 3) {
                    this.crtPointStatement(userId, PointsChannelEnum.ADOPT_INFO_BROWSE.getPoints(), targetId, PointsChannelEnum.ADOPT_INFO_BROWSE);
                    points = PointsChannelEnum.ADOPT_INFO_BROWSE.getPoints();
                }
                break;
            case "6":
                if (pointsStatementList != null && pointsStatementList.size() == 0) {
                    this.crtPointStatement(userId, PointsChannelEnum.GROUP_ACTIVITY_SHARE.getPoints(), targetId, PointsChannelEnum.GROUP_ACTIVITY_SHARE);
                    points = PointsChannelEnum.GROUP_ACTIVITY_SHARE.getPoints();
                }
                break;
            case "7":
                this.crtPointStatement(userId, PointsChannelEnum.GROUP_ACTIVITY_ATTEND.getPoints(), targetId, PointsChannelEnum.GROUP_ACTIVITY_ATTEND);
                points = PointsChannelEnum.GROUP_ACTIVITY_ATTEND.getPoints();
                break;
            case "8":
                if (pointsStatementList != null && pointsStatementList.size() < 5) {
                    this.crtPointStatement(userId, PointsChannelEnum.GROUP_POST.getPoints(), targetId, PointsChannelEnum.GROUP_POST);
                    points = PointsChannelEnum.GROUP_POST.getPoints();
                }
                break;
            default:
                break;
        }
        return points;
    }

    @Override
    public List<CmsAdoptPointsStatement> getPointStatementList(String userId) {
        return pointsStatementMapper.getPointStatementList(userId);
    }

    @Override
    public List<CmsAdoptPointsStatement> getPointStatementListByChannel(String userId, String channel) {
        return pointsStatementMapper.getPointStatementListByChannel(userId, channel);
    }

    @Override
    public Integer getUserPoints(String userId) {
        return pointsStatementMapper.getUserPoints(userId);
    }
}
