package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptGroupActivityRegister;

import java.util.List;

public interface CmsAdoptGroupActivityRegisterMapper {
    int deleteByPrimaryKey(String registerId);

    int insert(CmsAdoptGroupActivityRegister record);

    int insertSelective(CmsAdoptGroupActivityRegister record);

    CmsAdoptGroupActivityRegister selectByPrimaryKey(String registerId);

    int updateByPrimaryKeySelective(CmsAdoptGroupActivityRegister record);

    int updateByPrimaryKey(CmsAdoptGroupActivityRegister record);

    /**
     * 根据活动id获取用户活动报名信息
     *
     * @param userId
     * @param activityId
     * @return
     */
    CmsAdoptGroupActivityRegister getGroupActivityRegister(String userId, String activityId);
}