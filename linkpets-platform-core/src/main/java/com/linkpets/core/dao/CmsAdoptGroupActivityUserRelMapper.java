package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptGroupActivityUserRel;

public interface CmsAdoptGroupActivityUserRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsAdoptGroupActivityUserRel record);

    int insertSelective(CmsAdoptGroupActivityUserRel record);

    CmsAdoptGroupActivityUserRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsAdoptGroupActivityUserRel record);

    int updateByPrimaryKey(CmsAdoptGroupActivityUserRel record);

    /**
     * 获取用户关注圈子活动记录
     * @param userId
     * @param activityId
     * @return
     */
    CmsAdoptGroupActivityUserRel getFollowUserByUserIdAndActivityId(String userId, String activityId);
}