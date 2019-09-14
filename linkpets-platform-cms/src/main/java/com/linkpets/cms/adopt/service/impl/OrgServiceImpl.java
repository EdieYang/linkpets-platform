package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IOrgService;
import com.linkpets.core.dao.*;
import com.linkpets.core.model.*;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class OrgServiceImpl implements IOrgService {

    @Resource
    private CmsAdoptOrgMapper orgMapper;

    @Resource
    private CmsAdoptOrgFollowMapper orgFollowMapper;

    @Resource
    private CmsAdoptPetMapper cmsAdoptPetMapper;

    @Resource
    private CmsAdoptOrgActivityMapper cmsAdoptOrgActivityMapper;

    @Resource
    private CmsAdoptOrgGalleryMapper cmsAdoptOrgGalleryMapper;
    
    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public List<CmsAdoptOrg> getAdoptOrgList() {
        return orgMapper.getAdoptOrgList();
    }

    @Override
    public CmsAdoptOrg getAdoptOrgDetail(String orgId) {
        return orgMapper.selectByPrimaryKey(orgId);
    }

    @Override
    public String insertOrg(CmsAdoptOrg org) {
        String orgId = UUIDUtils.getUUID();
        org.setOrgId(orgId);
        org.setCreateDate(new Date());
        orgMapper.insertSelective(org);
        return orgId;
    }

    @Override
    public void uptOrg(CmsAdoptOrg org) {
        orgMapper.updateByPrimaryKeySelective(org);
    }

    @Override
    public List<CmsAdoptOrgFollow> getAdoptOrgFollows(String orgId) {
        return orgFollowMapper.getOrgFollowByOrgId(orgId);
    }

    @Override
    public int getAdoptOrgFollowNums(String orgId) {
        return orgFollowMapper.getOrgFollowNum(orgId);
    }

    @Override
    public void crtAdoptOrgFollow(String orgId, String userId) {
        CmsAdoptOrgFollow orgFollow = orgFollowMapper.getOrgUserFollowByOrgId(orgId, userId);
        if (orgFollow == null) {
            orgFollow = new CmsAdoptOrgFollow();
            orgFollow.setId(UUIDUtils.getUUID());
            orgFollow.setOrgId(orgId);
            orgFollow.setUserId(userId);
            orgFollow.setCreateDate(new Date());
            orgFollow.setIsValid(1);
            orgFollowMapper.insertOrgFollow(orgFollow);
        } else {
            orgFollow.setIsValid(1);
            orgFollowMapper.uptOrgFollow(orgFollow);
        }

    }

    @Override
    public void uptAdoptOrgFollow(String orgId, String userId) {
        CmsAdoptOrgFollow orgFollow = orgFollowMapper.getOrgUserFollowByOrgId(orgId, userId);
        if (orgFollow != null) {
            orgFollow.setIsValid(0);
            orgFollowMapper.uptOrgFollow(orgFollow);
        }
    }

    @Override
    public CmsAdoptOrgStatistic getAdoptOrgStatistic(String orgId, String userId) {
        return orgMapper.getAdoptOrgStatistic(orgId, userId);
    }

    @Override
    public PageInfo<CmsAdoptPet> getAdoptListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CmsAdoptPet> list = cmsAdoptPetMapper.getOrgPetsList(param);
        PageInfo<CmsAdoptPet> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public PageInfo<CmsAdoptOrgGallery> getAdoptGalleryList(int pageNum, int pageSize, String orgId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptOrgGallery> list = cmsAdoptOrgGalleryMapper.getAdoptGalleryList(orgId);
        PageInfo<CmsAdoptOrgGallery> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public PageInfo<CmsAdoptOrgActivity> getAdoptActivityList(int pageNum, int pageSize, String orgId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptOrgActivity> list = cmsAdoptOrgActivityMapper.getAdoptActivityList(orgId);
        PageInfo<CmsAdoptOrgActivity> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public CmsAdoptPetOrgRel getAdoptPetOrgInfoByPetId(String petId) {
        return orgMapper.getAdoptPetOrgInfoByPetId(petId);
    }


    @Override
    public void crtAdoptOrgPet(String orgId, String petId) {
        orgMapper.crtAdoptOrgPetRel(UUIDUtils.getUUID(), orgId, petId);
    }

    @Override
    public void uptAdoptOrgPet(String id) {
        orgMapper.uptAdoptOrgPetRel(id);
    }

    @Override
    public String insertActivity(CmsAdoptOrgActivity activity) {
        String activityId = UUIDUtils.getUUID();
        activity.setId(activityId);
        activity.setCreateDate(new Date());
        cmsAdoptOrgActivityMapper.insertSelective(activity);
        return activityId;
    }

    @Override
    public void uptActivity(CmsAdoptOrgActivity activity) {
        cmsAdoptOrgActivityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public void insertGallery(CmsAdoptOrgGallery gallery) {
        cmsAdoptOrgGalleryMapper.insertSelective(gallery);
    }

    @Override
    public void delGallery(CmsAdoptOrgGallery gallery) {
        cmsAdoptOrgGalleryMapper.updateByPrimaryKeySelective(gallery);
    }

	@Override
	public PageInfo<Map<String, Object>> getOrgUserList(String orgId, int pageNum, int pageSize, String orderBy) {
		PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Map<String, Object>> list = sysUserMapper.getOrgUserList(orgId);
        PageInfo<Map<String, Object>> page = new PageInfo<>(list);
        return page;
	}
}
