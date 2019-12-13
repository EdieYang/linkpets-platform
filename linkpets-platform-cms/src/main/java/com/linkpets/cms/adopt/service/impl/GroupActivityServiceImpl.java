package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IGroupActivityService;
import com.linkpets.core.dao.CmsAdoptGroupActivityMapper;
import com.linkpets.core.model.CmsAdoptGroupActivity;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Edie
 */
@Service
public class GroupActivityServiceImpl implements IGroupActivityService {

    @Resource
    private CmsAdoptGroupActivityMapper cmsAdoptGroupActivityMapper;

    @Override
    public List<CmsAdoptGroupActivity> getAdoptGroupActivityList(String groupId, Integer isActive) {
        return cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(groupId, isActive);
    }

    @Override
    public PageInfo<CmsAdoptGroupActivity> getAdoptGroupActivityPage(String groupId, Integer isActive, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptGroupActivity> cmsAdoptGroupActivityList = cmsAdoptGroupActivityMapper.getAdoptGroupActivityList(groupId, isActive);
        PageInfo<CmsAdoptGroupActivity> cmsAdoptGroupActivityPageInfo = new PageInfo<>(cmsAdoptGroupActivityList);
        return cmsAdoptGroupActivityPageInfo;
    }

    @Override
    public CmsAdoptGroupActivity getAdoptGroupActivityInfo(String activityId) {
        return cmsAdoptGroupActivityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public String crtAdoptGroupActivity(CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        String activityId = UUIDUtils.getUUID();
        cmsAdoptGroupActivity.setId(activityId);
        cmsAdoptGroupActivity.setCreateDate(new Date());
        cmsAdoptGroupActivityMapper.insertSelective(cmsAdoptGroupActivity);
        return activityId;
    }

    @Override
    public void uptAdoptGroupActivity(CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        cmsAdoptGroupActivityMapper.updateByPrimaryKeySelective(cmsAdoptGroupActivity);
    }
}
