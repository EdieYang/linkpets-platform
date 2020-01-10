package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupActivityUserRel;

public interface CmsGroupActivityUserRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsGroupActivityUserRel record);

    int insertSelective(CmsGroupActivityUserRel record);

    CmsGroupActivityUserRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsGroupActivityUserRel record);

    int updateByPrimaryKey(CmsGroupActivityUserRel record);

    /**
     * 获取用户关注圈子活动记录
     * @param userId
     * @param activityId
     * @return
     */
    CmsGroupActivityUserRel getFollowUserByUserIdAndActivityId(String userId, String activityId);
}