package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupActivityRegister;
import com.linkpets.core.respEntity.RespActivityRegister;

import java.util.List;

public interface CmsAdoptGroupActivityRegisterMapper {
    int deleteByPrimaryKey(String registerId);

    int insert(CmsGroupActivityRegister record);

    int insertSelective(CmsGroupActivityRegister record);

    CmsGroupActivityRegister selectByPrimaryKey(String registerId);

    int updateByPrimaryKeySelective(CmsGroupActivityRegister record);

    int updateByPrimaryKey(CmsGroupActivityRegister record);

    /**
     * 根据活动id获取用户活动报名信息
     *
     * @param userId
     * @param activityId
     * @return
     */
    CmsGroupActivityRegister getGroupActivityRegister(String userId, String activityId);

    /**
     * 获取活动报名明细列表
     *
     * @param activityId
     * @param isValid
     * @return
     */
    List<RespActivityRegister> getGroupActivityRegisterInfoList(String activityId, Integer isValid, String wxAccount, String mobilePhone, String involvementTime);

    /**
     * 获取用户活动报名
     *
     * @param userId
     * @param activityId
     * @return
     */
    CmsGroupActivityRegister getGroupActivityRegisterListByUserId(String userId, String activityId);

    /**
     * 获取活动报名列表
     *
     * @param activityId
     * @return
     */
    List<CmsGroupActivityRegister> getGroupActivityRegisterListByActivityId(String activityId);

}