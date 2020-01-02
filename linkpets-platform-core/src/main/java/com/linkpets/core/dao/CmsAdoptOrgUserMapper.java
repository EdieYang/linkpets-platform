package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptOrgUser;

public interface CmsAdoptOrgUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsAdoptOrgUser record);

    int insertSelective(CmsAdoptOrgUser record);

    CmsAdoptOrgUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsAdoptOrgUser record);

    int updateByPrimaryKey(CmsAdoptOrgUser record);

    CmsAdoptOrgUser selectByUserIdAndOrgIdAndSysUserId(String userId, String sysUserId, String orgId);
}