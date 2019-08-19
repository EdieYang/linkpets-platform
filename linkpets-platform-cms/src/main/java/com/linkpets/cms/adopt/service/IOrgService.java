package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.*;

import java.util.List;
import java.util.Map;

public interface IOrgService {

    List<CmsAdoptOrg> getAdoptOrgList();

    CmsAdoptOrg getAdoptOrgDetail(String orgId);

    List<CmsAdoptOrgFollow> getAdoptOrgFollows(String orgId);

    int getAdoptOrgFollowNums(String orgId);

    void crtAdoptOrgFollow(String orgId,String userId);

    void uptAdoptOrgFollow(String orgId,String userId);

    AdoptOrgStatistic getAdoptOrgStatistic(String orgId,String userId);

    PageInfo<CmsAdoptPet> getAdoptListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    PageInfo<CmsAdoptOrgGallery> getAdoptGalleryList(int pageNum, int pageSize, String orgId);

    PageInfo<CmsAdoptOrgActivity> getAdoptActivityList(int pageNum, int pageSize, String orgId);

    CmsAdoptPetOrgRel getAdoptPetOrgInfoByPetId(String petId);

    void crtAdoptOrgPet(String orgId,String petId);

    void uptAdoptOrgPet(String id);
}
