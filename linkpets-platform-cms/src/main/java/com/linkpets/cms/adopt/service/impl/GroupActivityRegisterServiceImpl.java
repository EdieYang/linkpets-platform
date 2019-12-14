package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IGroupActivityRegisterService;
import com.linkpets.core.dao.CmsAdoptGroupActivityMapper;
import com.linkpets.core.dao.CmsAdoptGroupActivityRegisterMapper;
import com.linkpets.core.model.CmsAdoptGroupActivityRegister;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Edie
 */
@Service
public class GroupActivityRegisterServiceImpl implements IGroupActivityRegisterService {

    @Resource
    private CmsAdoptGroupActivityRegisterMapper activityRegisterMapper;

    @Override
    public List<CmsAdoptGroupActivityRegister> getGroupActivityRegisterList(String activityId) {
        return null;
    }

    @Override
    public PageInfo<CmsAdoptGroupActivityRegister> getGroupActivityRegisterPage(String activityId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtGroupActivityRegister(String userId, String activityId) {
        CmsAdoptGroupActivityRegister activityRegister = activityRegisterMapper.getGroupActivityRegister(userId, activityId);
        if (activityRegister != null) {
            return activityRegister.getRegisterId();
        }
        //TODO 扣除用户积分

        activityRegister = new CmsAdoptGroupActivityRegister();
        String registerId = UUIDUtils.getUUID();
        activityRegister.setRegisterId(registerId);
        activityRegister.setActivityId(activityId);
        activityRegister.setUserId(userId);
        activityRegister.setCreateDate(new Date());
        activityRegister.setIsPaid(1);
        activityRegisterMapper.insertSelective(activityRegister);
        return registerId;
    }
}
