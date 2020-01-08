package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptGroupActivityRegister;
import com.linkpets.core.respEntity.RespActivityRegister;

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
    CmsAdoptGroupActivityRegister getGroupActivityRegisterListByUserId(String userId, String activityId);

    /**
     * 获取活动报名列表
     *
     * @param activityId
     * @return
     */
    List<CmsAdoptGroupActivityRegister> getGroupActivityRegisterListByActivityId(String activityId);

}