package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptOrgFollow;

import java.util.List;

public interface CmsAdoptOrgFollowMapper {

    /**
     * 获取公益机构用户关注列表
     *
     * @param orgId
     * @return
     */
    List<CmsAdoptOrgFollow> getOrgFollowByOrgId(String orgId);

    /**
     * 获取关注详情
     *
     * @param orgId
     * @param userId
     * @return
     */
    CmsAdoptOrgFollow getOrgUserFollowByOrgId(String orgId, String userId);

    /**
     * 添加关注
     *
     * @param cmsAdoptOrgFollow
     */
    void insertOrgFollow(CmsAdoptOrgFollow cmsAdoptOrgFollow);

    /**
     * upt关注
     *
     * @param cmsAdoptOrgFollow
     */
    void uptOrgFollow(CmsAdoptOrgFollow cmsAdoptOrgFollow);

    /**
     * 获取公益机构用户关注数
     *
     * @param orgId
     * @return
     */
    int getOrgFollowNum(String orgId);
}
