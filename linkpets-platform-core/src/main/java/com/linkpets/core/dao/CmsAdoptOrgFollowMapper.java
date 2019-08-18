package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptOrgFollow;

import java.util.List;

public interface CmsAdoptOrgFollowMapper {

    List<CmsAdoptOrgFollow> getOrgFollowByOrgId(String orgId);

    void insertOrgFollow(CmsAdoptOrgFollow cmsAdoptOrgFollow);

    void uptOrgFollow(CmsAdoptOrgFollow cmsAdoptOrgFollow);

    int getOrgFollowNum(String orgId);
}
