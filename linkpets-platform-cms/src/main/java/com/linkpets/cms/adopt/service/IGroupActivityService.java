package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptGroupActivity;
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
    List<CmsAdoptGroupActivity> getAdoptGroupActivityList(Integer activityType, Integer isActive);

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
     * 获取圈子活动详情
     *
     * @param activityId
     * @return
     */
    CmsAdoptGroupActivity getAdoptGroupActivityInfo(String activityId);

    /**
     * 创建圈子活动
     *
     * @param cmsAdoptGroupActivity
     * @return
     */
    String crtAdoptGroupActivity(CmsAdoptGroupActivity cmsAdoptGroupActivity);

    /**
     * 更新圈子活动
     *
     * @param cmsAdoptGroupActivity
     */
    void uptAdoptGroupActivity(CmsAdoptGroupActivity cmsAdoptGroupActivity);

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
