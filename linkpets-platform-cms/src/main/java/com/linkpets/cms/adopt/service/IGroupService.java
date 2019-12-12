package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptGroup;

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
}
