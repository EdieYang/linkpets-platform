package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptGroup;

import java.util.List;

/**
 * @Author Edie
 */
public interface IGroupService {

    /**
     * 分页查询圈子列表
     *
     * @param groupType
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CmsAdoptGroup> getAdoptGroupPage(String groupType, Integer pageNum, Integer pageSize);

    /**
     * 获取圈子详情
     * @param groupId
     * @return
     */
    CmsAdoptGroup getAdoptGroup(String groupId);

    /**
     * 创建圈子
     *
     * @param cmsAdoptGroup
     * @return
     */
    String crtAdoptGroup(CmsAdoptGroup cmsAdoptGroup);

    /**
     * 更新圈子
     * @param cmsAdoptGroup
     */
    void uptAdoptGroup(CmsAdoptGroup cmsAdoptGroup);

    /**
     * 获取用户关注的圈子列表
     * @param userId
     * @return
     */
    List<CmsAdoptGroup> getFollowedGroupList(String userId);

    /**
     * 关注圈子
     * @param userId
     * @param groupId
     */
    void followGroup(String userId,String groupId);

    /**
     * 取消关注圈子
     */
    void unFollowGroup(String userId,String groupId);


}
