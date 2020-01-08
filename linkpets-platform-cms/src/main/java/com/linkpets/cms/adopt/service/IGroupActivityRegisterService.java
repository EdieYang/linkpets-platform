package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptGroupActivityRegister;
import com.linkpets.core.respEntity.RespActivityRegister;

import java.util.List;

/**
 * @Author Edie
 */
public interface IGroupActivityRegisterService {

    /**
     * 获取活动报名明细列表
     *
     * @param activityId
     * @return
     */
    List<RespActivityRegister> getGroupActivityRegisterList(String activityId, Integer isValid, String wxAccount, String mobilePhone, String involvementTime);


    /**
     * 分页获取活动报名明细列表
     *
     * @param activityId
     * @return
     */
    PageInfo<RespActivityRegister> getGroupActivityRegisterPage(String activityId, Integer isValid, String wxAccount, String mobilePhone, String involvementTime, Integer pageNum, Integer pageSize);


    /**
     * 活动报名
     *
     * @param userId
     * @param activityId
     */
    String crtGroupActivityRegister(String userId, String activityId, String involvementTime);

    /**
     * 获取用户报名记录
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

    /**
     * 取消用户活动报名
     *
     * @param userId
     * @param activityId
     * @param memo
     */
    void delGroupActivityRegister(String userId, String activityId, String memo);


}
