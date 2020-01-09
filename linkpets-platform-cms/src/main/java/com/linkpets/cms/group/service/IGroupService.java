package com.linkpets.cms.group.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsGroup;
import com.linkpets.core.respEntity.RespGroupInfo;

import java.util.List;

/**
 * @Author Edie
 */
public interface IGroupService {

    /**
     * 分页查询圈子列表
     *
     * @param groupType
     * @param isActive
     * @param orderBy
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RespGroupInfo> getAdoptGroupPage(String groupType, Integer isActive, Integer orderBy, Integer pageNum, Integer pageSize);

    /**
     * 查询圈子列表
     *
     * @param groupType
     * @param isActive
     * @param orderBy
     * @return
     */
    List<RespGroupInfo> getAdoptGroupList(String groupType, Integer isActive, Integer orderBy);

    /**
     * 查询活动圈子列表
     *
     * @return
     */
    List<CmsGroup> getActivityGroupList();

    /**
     * 获取圈子详情
     *
     * @param groupId
     * @param userId
     * @return
     */
    RespGroupInfo getAdoptGroup(String groupId, String userId);

    /**
     * 创建圈子
     *
     * @param cmsGroup
     * @return
     */
    String crtAdoptGroup(CmsGroup cmsGroup);

    /**
     * 更新圈子
     *
     * @param cmsGroup
     */
    void uptAdoptGroup(CmsGroup cmsGroup);

    /**
     * 获取用户关注的圈子列表
     *
     * @param userId
     * @return
     */
    List<CmsGroup> getFollowedGroupList(String userId);

    /**
     * 关注圈子
     *
     * @param userId
     * @param groupId
     */
    void followGroup(String userId, String groupId);

    /**
     * 取消关注圈子
     */
    void unFollowGroup(String userId, String groupId);


}
