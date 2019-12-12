package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IGroupService;
import com.linkpets.core.dao.CmsAdoptGroupMapper;
import com.linkpets.core.dao.CmsAdoptGroupUserRelMapper;
import com.linkpets.core.model.CmsAdoptGroup;
import com.linkpets.core.model.CmsAdoptGroupUserRel;
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
    public PageInfo<CmsAdoptGroup> getAdoptGroupPage(String groupType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptGroup> cmsAdoptGroupList = cmsAdoptGroupMapper.getAdoptGroupList(groupType);
        PageInfo<CmsAdoptGroup> cmsAdoptGroupPageInfo = new PageInfo<>(cmsAdoptGroupList);
        return cmsAdoptGroupPageInfo;
    }

    @Override
    public CmsAdoptGroup getAdoptGroup(String groupId) {
        return cmsAdoptGroupMapper.selectByPrimaryKey(groupId);
    }

    @Override
    public String crtAdoptGroup(CmsAdoptGroup cmsAdoptGroup) {
        String groupId = UUIDUtils.getUUID();
        cmsAdoptGroup.setGroupId(groupId);
        cmsAdoptGroup.setCreateDate(new Date());
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
