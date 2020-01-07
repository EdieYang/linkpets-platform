package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.enums.PointsChannelEnum;
import com.linkpets.cms.adopt.service.IGroupActivityRegisterService;
import com.linkpets.cms.adopt.service.IGroupActivityService;
import com.linkpets.cms.adopt.service.IPointStatementService;
import com.linkpets.core.dao.CmsAdoptGroupActivityMapper;
import com.linkpets.core.dao.CmsAdoptGroupActivityRegisterMapper;
import com.linkpets.core.model.CmsAdoptGroupActivity;
import com.linkpets.core.model.CmsAdoptGroupActivityRegister;
import com.linkpets.core.respEntity.RespActivityRegister;
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
    @Resource
    private IGroupActivityService groupActivityService;
    @Resource
    private IPointStatementService pointStatementService;

    @Override
    public List<RespActivityRegister> getGroupActivityRegisterList(String activityId) {
        return activityRegisterMapper.getGroupActivityRegisterInfoList(activityId);
    }

    @Override
    public PageInfo<RespActivityRegister> getGroupActivityRegisterPage(String activityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespActivityRegister> activityRegisterList = activityRegisterMapper.getGroupActivityRegisterInfoList(activityId);
        return new PageInfo<>(activityRegisterList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtGroupActivityRegister(String userId, String activityId) {
        CmsAdoptGroupActivity activity = groupActivityService.getAdoptGroupActivityInfo(activityId);
        //TODO 扣除用户积分
        if (activity == null) {
            return null;
        }
        if (activity.getActivityCost() != 0) {
            pointStatementService.crtPointStatement(userId, 0 - activity.getActivityCost(), activityId, PointsChannelEnum.GROUP_ACTIVITY_REGISTER);
        }
        CmsAdoptGroupActivityRegister activityRegister = new CmsAdoptGroupActivityRegister();
        String registerId = UUIDUtils.getId();
        activityRegister.setRegisterId(registerId);
        activityRegister.setActivityId(activityId);
        activityRegister.setUserId(userId);
        activityRegister.setCreateDate(new Date());
        activityRegister.setIsPaid(1);
        activityRegisterMapper.insertSelective(activityRegister);
        return registerId;
    }

    @Override
    public List<CmsAdoptGroupActivityRegister> getGroupActivityRegisterListByUserId(String userId, String activityId) {
        return activityRegisterMapper.getGroupActivityRegisterListByUserId(userId, activityId);
    }
}
