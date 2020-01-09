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
    List<CmsGroupActivity> getAdoptGroupActivityList(Integer activityType, Integer isActive);

    /**
     * 分页获取圈子活动列表
     *
     * @param activityType
     * @param isActive
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RespGroupActivity> getAdoptGroupActivityPage(Integer activityType, Integer isActive, Integer pageNum, Integer pageSize);

    /**
     * 查询用户关注的圈子活动列表
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
    CmsGroupActivity getAdoptGroupActivityInfo(String activityId);

    /**
     * 创建圈子活动
     *
     * @param cmsGroupActivity
     * @return
     */
    String crtAdoptGroupActivity(CmsGroupActivity cmsGroupActivity);

    /**
     * 更新圈子活动
     *
     * @param cmsGroupActivity
     */
    void uptAdoptGroupActivity(CmsGroupActivity cmsGroupActivity);

    /**
     * 删除圈子活动
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
     * @param userId
     * @param activityId
     */
    void delGroupActivityFollow(String userId, String activityId);

}
