package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptGroupActivityRegister;

import java.util.List;

/**
 * @Author Edie
 */
public interface IGroupActivityRegisterService {

    /**
     * 获取活动报名明细列表
     * @param activityId
     * @return
     */
    List<CmsAdoptGroupActivityRegister> getGroupActivityRegisterList(String activityId);


    /**
     * 分页获取活动报名明细列表
     * @param activityId
     * @return
     */
    PageInfo<CmsAdoptGroupActivityRegister> getGroupActivityRegisterPage(String activityId,Integer pageNum,Integer pageSize);


    /**
     * 活动报名
     * @param userId
     * @param activityId
     */
    String crtGroupActivityRegister(String userId,String activityId);


}
