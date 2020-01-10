package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IGroupService;
import com.linkpets.core.dao.CmsGroupMapper;
import com.linkpets.core.dao.CmsGroupUserRelMapper;
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
    private CmsGroupMapper CmsGroupMapper;

    @Resource
    private CmsGroupUserRelMapper cmsGroupUserRelMapper;

    @Override
    public PageInfo<RespGroupInfo> getGroupPage(String groupType, Integer isActive, Integer orderBy, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupInfo> cmsGroupList = CmsGroupMapper.getGroupList(groupType, isActive, orderBy);
        PageInfo<RespGroupInfo> cmsGroupPageInfo = new PageInfo<>(cmsGroupList);
        return cmsGroupPageInfo;
    }

    @Override
    public List<RespGroupInfo> getGroupList(String groupType, Integer isActive, Integer orderBy) {
        List<RespGroupInfo> cmsGroupList = CmsGroupMapper.getGroupList(groupType, isActive, orderBy);
        return cmsGroupList;
    }

    @Override
    public List<CmsGroup> getActivityGroupList() {
        return CmsGroupMapper.getActivityGroupList();
    }

    @Override
    public RespGroupInfo getGroup(String groupId, String userId) {
        RespGroupInfo groupInfo = CmsGroupMapper.getGroup(groupId, null);
        if (StringUtils.isNotEmpty(userId)) {
            CmsGroupUserRel groupUserRel = cmsGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
            if (groupUserRel == null) {
                groupInfo.setIsFollowed(0);
            } else {
                groupInfo.setIsFollowed(1);
            }
        }
        return groupInfo;
    }

    @Override
    public String crtGroup(CmsGroup cmsGroup) {
        String groupId = UUIDUtils.getId();
        cmsGroup.setGroupId(groupId);
        cmsGroup.setCreateDate(new Date());
        cmsGroup.setSort(999);
        CmsGroupMapper.insertSelective(cmsGroup);
        return groupId;
    }

    @Override
    public void uptGroup(CmsGroup cmsGroup) {
        CmsGroupMapper.updateByPrimaryKeySelective(cmsGroup);
    }

    @Override
    public List<CmsGroup> getFollowedGroupList(String userId) {
        return CmsGroupMapper.getFollowedGroupList(userId);
    }

    @Override
    public void followGroup(String userId, String groupId) {
        CmsGroupUserRel cmsGroupUserRel = cmsGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
        if (cmsGroupUserRel != null) {
            cmsGroupUserRel.setIsValid(1);
            cmsGroupUserRel.setCreateDate(new Date());
            cmsGroupUserRelMapper.updateByPrimaryKeySelective(cmsGroupUserRel);
        } else {
            cmsGroupUserRel = new CmsGroupUserRel();
            cmsGroupUserRel.setUserId(userId);
            cmsGroupUserRel.setGroupId(groupId);
            cmsGroupUserRel.setCreateDate(new Date());
            cmsGroupUserRel.setId(UUIDUtils.getId());
            cmsGroupUserRelMapper.insertSelective(cmsGroupUserRel);
        }

    }

    @Override
    public void unFollowGroup(String userId, String groupId) {
        CmsGroupUserRel cmsGroupUserRel = cmsGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
        if (cmsGroupUserRel != null) {
            cmsGroupUserRel.setIsValid(0);
            cmsGroupUserRelMapper.updateByPrimaryKeySelective(cmsGroupUserRel);
        }
    }
}
