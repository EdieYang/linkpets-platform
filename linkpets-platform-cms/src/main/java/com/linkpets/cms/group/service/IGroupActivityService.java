package com.linkpets.cms.group.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.respEntity.RespGroupActivity;

import java.util.List;

public interface IGroupActivityService {

    /**
     * 查询圈子活动列表
     *
     * @param activityType
     * @param isActive
     * @return
     */
    List<CmsGroupActivity> getGroupActivityList(Integer activityType, Integer isActive);

    /**
     * 分页获取圈子活动列表
     *
     * @param activityType
     * @param isActive
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RespGroupActivity> getGroupActivityPage(Integer activityType, Integer isActive, Integer pageNum, Integer pageSize);

    /**
     * 查询开始时间在时间范围内的圈子活动列表
     *
     * @param rangeStartDate
     * @param rangeEndDate
     * @return
     */
    List<CmsGroupActivity> getGroupActivityListWithTimeRange(String rangeStartDate, String rangeEndDate);

    /**
     * 查询用户关注的圈子活动列表
     *
     * @param userId
     * @return
     */
    List<RespGroupActivity> getGroupActivityListByUserId(String userId);


    /**
     * 获取圈子活动详情
     *
     * @param activityId
     * @return
     */
    CmsGroupActivity getGroupActivityInfo(String activityId);


    /**
     * 获取圈子活动详情
     *
     * @param activityId
     * @return
     */
    CmsGroupActivity getGroupActivityInfoWithUserId(String activityId, String userId);


    /**
     * 创建圈子活动
     *
     * @param cmsGroupActivity
     * @return
     */
    String crtGroupActivity(CmsGroupActivity cmsGroupActivity);

    /**
     * 更新圈子活动
     *
     * @param cmsGroupActivity
     */
    void uptGroupActivity(CmsGroupActivity cmsGroupActivity);

    /**
     * 删除圈子活动
     *
     * @param activityId
     */
    void delGroupActivity(String activityId);

    /**
     * 关注圈子活动
     *
     * @param userId
     * @param activityId
     * @return
     */
    String crtGroupActivityFollow(String userId, String activityId);

    /**
     * 取消关注圈子活动
     *
     * @param userId
     * @param activityId
     */
    void delGroupActivityFollow(String userId, String activityId);

}
