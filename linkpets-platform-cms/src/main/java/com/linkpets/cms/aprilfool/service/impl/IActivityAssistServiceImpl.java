package com.linkpets.cms.aprilfool.service.impl;

import com.linkpets.cms.aprilfool.dao.CmsActivityAssistanceCustomMapper;
import com.linkpets.cms.aprilfool.dao.CmsActivityConfigCustomMapper;
import com.linkpets.cms.aprilfool.service.IActivityAssistService;
import com.linkpets.core.dao.CmsActivityAssistanceMapper;
import com.linkpets.core.dao.CmsActivityDraftMapper;
import com.linkpets.core.model.CmsActivityAssistance;
import com.linkpets.core.model.CmsActivityDraft;
import com.linkpets.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class IActivityAssistServiceImpl implements IActivityAssistService {

    @Autowired
    private CmsActivityAssistanceMapper activityAssistanceMapper;

    @Autowired
    private CmsActivityAssistanceCustomMapper activityAssistanceCustomMapper;

    @Autowired
    private CmsActivityConfigCustomMapper activityConfigCustomMapper;

    @Autowired
    private CmsActivityDraftMapper activityDraftMapper;


    private static final int UN_DRAFT_STATUS = 0;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createActivityAssistRecord(String activityId, String userId, int assistType, String assistUserId) {

        CmsActivityAssistance record = activityAssistanceCustomMapper.getActivityAssistanceByUserId(activityId, userId);
        if (record != null) {
            return record.getAssistId();
        }


        CmsActivityAssistance activityAssistance = new CmsActivityAssistance();
        activityAssistance.setAssistId(UUIDUtils.getUUID());
        activityAssistance.setActivityId(activityId);
        activityAssistance.setUserId(userId);
        activityAssistance.setAssistType(assistType);
        activityAssistance.setAssistUserId(assistUserId);
        activityAssistance.setCreateTime(new Date());
        activityAssistance.setAssistOrder(generateAssistOrder(activityId));
        activityAssistanceMapper.insertSelective(activityAssistance);

        //新增抽奖机会
        CmsActivityDraft activityDraft = new CmsActivityDraft();
        activityDraft.setDraftId(UUIDUtils.getUUID());
        activityDraft.setActivityId(activityId);
        activityDraft.setDraftStatus(UN_DRAFT_STATUS);
        activityDraft.setUserId(userId);
        activityDraft.setCreateTime(new Date());
        activityDraftMapper.insertSelective(activityDraft);
        return activityAssistance.getAssistId();
    }

    @Override
    public CmsActivityAssistance getActivityAssistRecordByUserId(String activityId, String userId) {
        return activityAssistanceCustomMapper.getActivityAssistanceByUserId(activityId, userId);
    }

//    @Override
//    public List<ActivityUser> getActivityAssistFollowersByAssistUserId(String activityId, String assistUserId) {
//        return activityAssistanceCustomMapper.getActivityAssistFollowersByAssistUserId(activityId,assistUserId);
//    }

    @Override
    public int getActivityAssistNo(String activityId) {
        return activityAssistanceCustomMapper.getActivityAssistNo(activityId);
    }


    public int generateAssistOrder(String activityId) {
        int assistOrderNO = 0;
        CmsActivityAssistance maxRecord = activityAssistanceCustomMapper.getMaxOrderOfActivityAssistanceByActivityId(activityId);
        int assistOrderBase = activityConfigCustomMapper.selectAssistNoByActivityId(activityId);

        if (maxRecord !=null && maxRecord.getAssistOrder() > assistOrderBase) {
            assistOrderNO = maxRecord.getAssistOrder() + 1;
        } else {
            assistOrderNO = assistOrderBase + 1;
        }
        return assistOrderNO;
    }
}
