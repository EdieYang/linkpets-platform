package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.respEntity.RespGroupActivity;

import java.util.List;

public interface CmsAdoptGroupActivityMapper {

    int deleteByPrimaryKey(String id);

    int insert(CmsGroupActivity record);

    int insertSelective(CmsGroupActivity record);

    CmsGroupActivity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsGroupActivity record);

    int updateByPrimaryKey(CmsGroupActivity record);

    /**
     * 查询圈子活动列表
     *
     * @param activityType
     * @param isActive
     * @return
     */
    List<CmsGroupActivity> getAdoptGroupActivityList(Integer activityType, Integer isActive);

    /**
     * 查询用户关注活动列表
     *
     * @param userId
     * @return
     */
    List<RespGroupActivity> getGroupActivityListByUserId(String userId);

    /**
     * 删除圈子活动
     *
     * @param activityId
     */
    void delGroupActivity(String activityId);
}