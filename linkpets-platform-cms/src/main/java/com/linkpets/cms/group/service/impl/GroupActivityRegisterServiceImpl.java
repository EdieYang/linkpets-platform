package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.enums.PointsChannelEnum;
import com.linkpets.cms.group.service.IGroupActivityRegisterService;
import com.linkpets.cms.group.service.IGroupActivityService;
import com.linkpets.cms.group.service.IPointStatementService;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.core.dao.CmsGroupActivityRegisterMapper;
import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.model.CmsGroupActivityRegister;
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
    private CmsGroupActivityRegisterMapper activityRegisterMapper;
    @Resource
    private IGroupActivityService groupActivityService;
    @Resource
    private IPointStatementService pointStatementService;

    @Override
    public List<RespActivityRegister> getGroupActivityRegisterList(String activityId, Integer isValid, String wxAccount, String mobilePhone, String involvementTime) {
        return activityRegisterMapper.getGroupActivityRegisterInfoList(activityId, isValid, wxAccount, mobilePhone, involvementTime);
    }

    @Override
    public PageInfo<RespActivityRegister> getGroupActivityRegisterPage(String activityId, Integer isValid, String wxAccount, String mobilePhone, String involvementTime, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespActivityRegister> activityRegisterList = activityRegisterMapper.getGroupActivityRegisterInfoList(activityId, isValid, wxAccount, mobilePhone, involvementTime);
        return new PageInfo<>(activityRegisterList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtGroupActivityRegister(String userId, String activityId, String involvementTime) {
        CmsGroupActivity activity = groupActivityService.getGroupActivityInfo(activityId);
        //扣除用户积分
        if (activity.getActivityCost() != 0) {
            pointStatementService.crtPointStatement(userId, 0 - activity.getActivityCost(), activityId, PointsChannelEnum.GROUP_ACTIVITY_REGISTER);
        }
        CmsGroupActivityRegister activityRegister = new CmsGroupActivityRegister();
        String registerId = UUIDUtils.getId();
        activityRegister.setRegisterId(registerId);
        activityRegister.setActivityId(activityId);
        activityRegister.setUserId(userId);
        activityRegister.setIsPaid(1);
        activityRegister.setInvolvementTime(involvementTime);
        activityRegister.setPaymentAmount(activity.getActivityCost());
        activityRegister.setCreateDate(new Date());
        activityRegisterMapper.insertSelective(activityRegister);
        //报名活动默认关注
        groupActivityService.crtGroupActivityFollow(userId, activityId);
        return registerId;
    }

    @Override
    public CmsGroupActivityRegister getGroupActivityRegisterListByUserId(String userId, String activityId) {
        return activityRegisterMapper.getGroupActivityRegisterListByUserId(userId, activityId);
    }

    @Override
    public List<CmsGroupActivityRegister> getGroupActivityRegisterListByActivityId(String activityId) {
        return activityRegisterMapper.getGroupActivityRegisterListByActivityId(activityId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delGroupActivityRegister(String userId, String activityId, String memo) {
        CmsGroupActivityRegister register = activityRegisterMapper.getGroupActivityRegisterListByUserId(userId, activityId);
        if (register != null) {
            //返回用户积分
            if (register.getPaymentAmount() != 0) {
                pointStatementService.crtPointStatement(userId, register.getPaymentAmount(), activityId, PointsChannelEnum.GROUP_ACTIVITY_SYSTEM_CANCEL);
            }
            CmsGroupActivityRegister cancelRegister = new CmsGroupActivityRegister();
            cancelRegister.setRegisterId(register.getRegisterId());
            cancelRegister.setIsValid(0);
            cancelRegister.setMemo(memo);
            activityRegisterMapper.updateByPrimaryKeySelective(cancelRegister);
        }
    }
}
