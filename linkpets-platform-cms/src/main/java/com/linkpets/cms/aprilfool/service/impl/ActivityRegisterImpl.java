package com.linkpets.cms.aprilfool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.aprilfool.service.IActivityRegisterService;
import com.linkpets.core.dao.CmsActivityRegistryInfoMapper;
import com.linkpets.core.dao.CmsActivityRegistryMapper;
import com.linkpets.core.model.CmsActivityRegistry;
import com.linkpets.core.model.CmsActivityRegistryInfo;
import com.linkpets.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class ActivityRegisterImpl implements IActivityRegisterService {

    @Resource
    private CmsActivityRegistryMapper activityRegistryMapper;

    @Resource
    private CmsActivityRegistryInfoMapper activityRegistryInfoMapper;

    private static final int CHECK_STATUS = 0;

    @Override
    public String registerActivity(String activityId, String userId, int registryType) {
        CmsActivityRegistry cmsActivityRegistry = new CmsActivityRegistry();
        cmsActivityRegistry.setRegistryId(UUIDUtils.getId());
        cmsActivityRegistry.setActivityId(activityId);
        cmsActivityRegistry.setUserId(userId);
        cmsActivityRegistry.setCreateTime(new Date());
        cmsActivityRegistry.setRegistryType(registryType);

        activityRegistryMapper.insertSelective(cmsActivityRegistry);
        return cmsActivityRegistry.getRegistryId();
    }

    @Override
    public CmsActivityRegistry getRegistryInfo(String registryId) {

        CmsActivityRegistry activityCustomRegistry = activityRegistryMapper.getRegistryInfo(registryId);
        return activityCustomRegistry;
    }

    @Override
    public List<CmsActivityRegistry> getRegistryInfoByUserId(String activityId, String userId) {
        List<CmsActivityRegistry> activityCustomRegistry = activityRegistryMapper.getRegistryInfoByUserId(activityId, userId);
        return activityCustomRegistry;
    }

    @Override
    public List<CmsActivityRegistry> getRegistryInfoList(String activityId) {

        List<CmsActivityRegistry> activityCustomRegistryList = activityRegistryMapper.getRegistryInfoList(activityId);
        return activityCustomRegistryList;
    }

    @Override
    public JSONObject registerQuestionInfo(String activityId, String userId, int registryType, String registerName, String registerPhone, String registerWx, String contentFirst, String contentSecond, String contentThird, String contentForth) {
        JSONObject result = new JSONObject();
        if (registryType != 0) {
            return result;
        }
        //查询报名问卷是否存在
        CmsActivityRegistryInfo activityRegistryInfo = activityRegistryInfoMapper.selectRegistryInfoByUserIdAndActivityId(userId, activityId);
        if (activityRegistryInfo != null) {
            result.put("data", activityRegistryInfo);
            return result;
        }
        //插入报名问卷
        CmsActivityRegistryInfo activityRegistryInfoCons = new CmsActivityRegistryInfo();
        activityRegistryInfoCons.setInfoId(UUIDUtils.getId());
        activityRegistryInfoCons.setActivityId(activityId);
        activityRegistryInfoCons.setUserId(userId);
        activityRegistryInfoCons.setRegisterName(registerName);
        activityRegistryInfoCons.setRegisterPhone(registerPhone);
        activityRegistryInfoCons.setRegisterWx(registerWx);
        activityRegistryInfoCons.setInfoContentFirst(contentFirst);
        activityRegistryInfoCons.setInfoContentSecond(contentSecond);
        activityRegistryInfoCons.setInfoContentThird(contentThird);
        activityRegistryInfoCons.setInfoContentForth(contentForth);
        activityRegistryInfoCons.setState(CHECK_STATUS);
        activityRegistryInfoCons.setCreateTime(new Date());

        activityRegistryInfoMapper.insertSelective(activityRegistryInfoCons);

        //同步注册活动表
        CmsActivityRegistry registry = new CmsActivityRegistry();
        registry.setRegistryId(UUIDUtils.getId());
        registry.setRegistryType(registryType);
        registry.setUserId(userId);
        registry.setActivityId(activityId);
        registry.setCreateTime(new Date());
        activityRegistryMapper.insertSelective(registry);

        result.put("data", registry.getRegistryId());
        return result;

    }

    @Override
    public CmsActivityRegistryInfo getRegisterQuestionInfo(String activityId, String userId) {
        CmsActivityRegistryInfo cmsActivityRegistryInfo = activityRegistryInfoMapper.selectRegistryInfoByUserIdAndActivityId(userId, activityId);
        return cmsActivityRegistryInfo;
    }


}
