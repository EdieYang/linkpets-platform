package com.linkpets.cms.group.service.impl;

import com.linkpets.cms.group.enums.PointsChannelEnum;
import com.linkpets.cms.group.service.IPointStatementService;
import com.linkpets.core.dao.CmsGroupActivityMapper;
import com.linkpets.core.dao.CmsPointsStatementMapper;
import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.model.CmsPointStatement;
import com.linkpets.util.DateUtils;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PointStatementServiceImpl implements IPointStatementService {

    @Resource
    private CmsPointsStatementMapper pointsStatementMapper;
    @Resource
    private CmsGroupActivityMapper activityMapper;

    @Override
    public void crtPointStatement(String userId, int points, String targetId, PointsChannelEnum pointsChannelEnum) {
        CmsPointStatement pointsStatement = new CmsPointStatement();
        pointsStatement.setId(UUIDUtils.getId());
        pointsStatement.setUserId(userId);
        pointsStatement.setTargetId(targetId);
        pointsStatement.setChannel(pointsChannelEnum.getChannel());
        pointsStatement.setPoints(points);
        pointsStatement.setCreateDate(new Date());
        pointsStatementMapper.insertSelective(pointsStatement);
    }

    @Override
    public Integer crtPointStatementByChannel(String userId, String targetId, int channel) {
        Integer points = 0;
        List<CmsPointStatement> pointsStatementList = pointsStatementMapper.getPointStatementListByChannelAndDay(userId, channel, DateUtils.getCurrentDay());
        switch (channel) {
            case 4:
                List<CmsPointStatement> sameSharePointStatement = pointsStatementMapper.getPointStatementListByTargetId(userId, channel, DateUtils.getCurrentDay(), targetId);
                if (sameSharePointStatement.size() == 0 && pointsStatementList != null && pointsStatementList.size() < 3) {
                    this.crtPointStatement(userId, PointsChannelEnum.ADOPT_INFO_SHARE.getPoints(), targetId, PointsChannelEnum.ADOPT_INFO_SHARE);
                    points = PointsChannelEnum.ADOPT_INFO_SHARE.getPoints();
                }
                break;
            case 5:
                List<CmsPointStatement> sameBrowsePointStatement = pointsStatementMapper.getPointStatementListByTargetId(userId, channel, DateUtils.getCurrentDay(), targetId);
                if (sameBrowsePointStatement.size() == 0 && pointsStatementList != null && pointsStatementList.size() < 3) {
                    this.crtPointStatement(userId, PointsChannelEnum.ADOPT_INFO_BROWSE.getPoints(), targetId, PointsChannelEnum.ADOPT_INFO_BROWSE);
                    points = PointsChannelEnum.ADOPT_INFO_BROWSE.getPoints();
                }
                break;
            case 6:
                if (pointsStatementList != null && pointsStatementList.size() == 0) {
                    this.crtPointStatement(userId, PointsChannelEnum.GROUP_ACTIVITY_SHARE.getPoints(), targetId, PointsChannelEnum.GROUP_ACTIVITY_SHARE);
                    points = PointsChannelEnum.GROUP_ACTIVITY_SHARE.getPoints();
                }
                break;
            case 7:
                CmsGroupActivity activity = activityMapper.selectByPrimaryKey(targetId);
                if (activity != null) {
                    //参加活动奖励积分去重
                    List<CmsPointStatement> cmsPointStatementList = pointsStatementMapper.getPointStatementListByTargetIdAndUserId(userId, targetId, channel);
                    if (cmsPointStatementList.size() == 0) {
                        this.crtPointStatement(userId, activity.getActivityPoint(), targetId, PointsChannelEnum.GROUP_ACTIVITY_ATTEND);
                        points = activity.getActivityPoint();
                    }
                }
                break;
            case 8:
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
    public List<CmsPointStatement> getPointStatementList(String userId) {
        return pointsStatementMapper.getPointStatementList(userId);
    }

    @Override
    public List<CmsPointStatement> getPointStatementListByChannel(String userId, int channel) {
        return pointsStatementMapper.getPointStatementListByChannel(userId, channel);
    }

    @Override
    public Integer getUserPoints(String userId) {
        return pointsStatementMapper.getUserPoints(userId);
    }
}
