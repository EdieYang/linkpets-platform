package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IGroupService;
import com.linkpets.core.dao.CmsAdoptGroupMapper;
import com.linkpets.core.dao.CmsAdoptGroupUserRelMapper;
import com.linkpets.core.model.CmsAdoptGroup;
import com.linkpets.core.model.CmsAdoptGroupUserRel;
import com.linkpets.core.respEntity.RespGroupInfo;
import com.linkpets.util.UUIDUtils;
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
    public RespGroupInfo getAdoptGroup(String groupId) {
        return cmsAdoptGroupMapper.getAdoptGroup(groupId);
    }

    @Override
    public String crtAdoptGroup(CmsAdoptGroup cmsAdoptGroup) {
        String groupId = UUIDUtils.getUUID();
        cmsAdoptGroup.setGroupId(groupId);
        cmsAdoptGroup.setCreateDate(new Date());
        cmsAdoptGroup.setSort(999);
        cmsAdoptGroupMapper.insertSelective(cmsAdoptGroup);
        return groupId;
    }

    @Override
    public void uptAdoptGroup(CmsAdoptGroup cmsAdoptGroup) {
        cmsAdoptGroupMapper.updateByPrimaryKeySelective(cmsAdoptGroup);
    }

    @Override
    public List<CmsAdoptGroup> getFollowedGroupList(String userId) {
        return cmsAdoptGroupMapper.getFollowedGroupList(userId);
    }

    @Override
    public void followGroup(String userId, String groupId) {
        CmsAdoptGroupUserRel cmsAdoptGroupUserRel = cmsAdoptGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
        if (cmsAdoptGroupUserRel != null) {
            cmsAdoptGroupUserRel.setIsValid(1);
            cmsAdoptGroupUserRel.setCreateDate(new Date());
            cmsAdoptGroupUserRelMapper.updateByPrimaryKeySelective(cmsAdoptGroupUserRel);
        } else {
            cmsAdoptGroupUserRel = new CmsAdoptGroupUserRel();
            cmsAdoptGroupUserRel.setUserId(userId);
            cmsAdoptGroupUserRel.setGroupId(groupId);
            cmsAdoptGroupUserRel.setCreateDate(new Date());
            cmsAdoptGroupUserRel.setId(UUIDUtils.getUUID());
            cmsAdoptGroupUserRelMapper.insertSelective(cmsAdoptGroupUserRel);
        }

    }

    @Override
    public void unFollowGroup(String userId, String groupId) {
        CmsAdoptGroupUserRel cmsAdoptGroupUserRel = cmsAdoptGroupUserRelMapper.selectByUserIdAndGroupId(userId, groupId);
        if (cmsAdoptGroupUserRel != null) {
            cmsAdoptGroupUserRel.setIsValid(0);
            cmsAdoptGroupUserRelMapper.updateByPrimaryKeySelective(cmsAdoptGroupUserRel);
        }
    }
}
