package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IGroupService;
import com.linkpets.core.dao.CmsAdoptGroupMapper;
import com.linkpets.core.dao.CmsAdoptGroupUserRelMapper;
import com.linkpets.core.model.CmsGroup;
import com.linkpets.core.model.CmsGroupUserRel;
import com.linkpets.core.respEntity.RespGroupInfo;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Edie
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Resource
    private CmsAdoptGroupMapper cmsAdoptGroupMapper;

    @Resource
    private CmsAdoptGroupUserRelMapper cmsAdoptGroupUserRelMapper;

    @Override
    public PageInfo<RespGroupInfo> getAdoptGroupPage(String groupType, Integer isActive, Integer orderBy, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupInfo> cmsAdoptGroupList = cmsAdoptGroupMapper.getAdoptGroupList(groupType, isActive, orderBy);
        PageInfo<RespGroupInfo> cmsAdoptGroupPageInfo = new PageInfo<>(cmsAdoptGroupList);
        return cmsAdoptGroupPageInfo;
    }

    @Override
    public List<RespGroupInfo> getAdoptGroupList(String groupType, Integer isActive, Integer orderBy) {
        List<RespGroupInfo> cmsAdoptGroupList = cmsAdoptGroupMapper.getAdoptGroupList(groupType, isActive, orderBy);
        return cmsAdoptGroupList;
    }

    @Override
    public List<CmsGroup> getActivityGroupList() {
        return cmsAdoptGroupMapper.getActivityGroupList();
    }

    @Override
    public RespGroupInfo getAdoptGroup(String groupId, String userId) {
        RespGroupInfo groupInfo = cmsAdoptGroupMapper.getAdoptGroup(groupId, null);
        if (StringUtils.isNotEmpty(userId)) {
            CmsGroupUserRel groupUserRel = cmsAdoptGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
            if (groupUserRel == null) {
                groupInfo.setIsFollowed(0);
            } else {
                groupInfo.setIsFollowed(1);
            }
        }
        return groupInfo;
    }

    @Override
    public String crtAdoptGroup(CmsGroup cmsGroup) {
        String groupId = UUIDUtils.getId();
        cmsGroup.setGroupId(groupId);
        cmsGroup.setCreateDate(new Date());
        cmsGroup.setSort(999);
        cmsAdoptGroupMapper.insertSelective(cmsGroup);
        return groupId;
    }

    @Override
    public void uptAdoptGroup(CmsGroup cmsGroup) {
        cmsAdoptGroupMapper.updateByPrimaryKeySelective(cmsGroup);
    }

    @Override
    public List<CmsGroup> getFollowedGroupList(String userId) {
        return cmsAdoptGroupMapper.getFollowedGroupList(userId);
    }

    @Override
    public void followGroup(String userId, String groupId) {
        CmsGroupUserRel cmsGroupUserRel = cmsAdoptGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
        if (cmsGroupUserRel != null) {
            cmsGroupUserRel.setIsValid(1);
            cmsGroupUserRel.setCreateDate(new Date());
            cmsAdoptGroupUserRelMapper.updateByPrimaryKeySelective(cmsGroupUserRel);
        } else {
            cmsGroupUserRel = new CmsGroupUserRel();
            cmsGroupUserRel.setUserId(userId);
            cmsGroupUserRel.setGroupId(groupId);
            cmsGroupUserRel.setCreateDate(new Date());
            cmsGroupUserRel.setId(UUIDUtils.getId());
            cmsAdoptGroupUserRelMapper.insertSelective(cmsGroupUserRel);
        }

    }

    @Override
    public void unFollowGroup(String userId, String groupId) {
        CmsGroupUserRel cmsGroupUserRel = cmsAdoptGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
        if (cmsGroupUserRel != null) {
            cmsGroupUserRel.setIsValid(0);
            cmsAdoptGroupUserRelMapper.updateByPrimaryKeySelective(cmsGroupUserRel);
        }
    }
}
