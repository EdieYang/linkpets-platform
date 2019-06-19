package com.linkpets.cms.aprilfool.service.impl;

import com.linkpets.cms.aprilfool.dao.CmsActivityDraftCustomMapper;
import com.linkpets.cms.aprilfool.model.CmsActivityCustomDraft;
import com.linkpets.cms.aprilfool.service.IActivityDraftService;
import com.linkpets.cms.coupon.dao.CmsCouponBatchItemCustomMapper;
import com.linkpets.cms.coupon.dao.CmsCouponCustomMapper;
import com.linkpets.core.dao.CmsActivityConfigMapper;
import com.linkpets.core.dao.CmsActivityDraftMapper;
import com.linkpets.core.dao.CmsActivityMapper;
import com.linkpets.core.model.*;
import com.linkpets.enums.PresentType;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author SteveYang
 * @date 2019/3/26
 */

@Service
public class ActivityDraftServiceImpl implements IActivityDraftService {

    private static final int DRAFTED = 1;
    private static final int UNDRAFTED = 0;


    @Autowired
    private CmsActivityDraftCustomMapper activityDraftCustomMapper;

    @Autowired
    private CmsActivityDraftMapper activityDrafMapper;

    @Autowired
    private CmsActivityConfigMapper activityConfigMapper;

    @Autowired
    private CmsCouponBatchItemCustomMapper batchItemCustomMapper;

    @Autowired
    private CmsCouponCustomMapper couponCustomMapper;

    @Autowired
    private CmsActivityMapper activityMapper;

    @Override
    public int getActivityDraftExistNoByUserId(String activityId, String userId) {
        return activityDraftCustomMapper.getActivityDraftExistNoByUserId(activityId, userId);
    }

    @Override
    public List<CmsActivityCustomDraft> getActivityDraftListByUserId(String activityId, String userId) {
        List<CmsActivityCustomDraft> customDrafts = new ArrayList<>();
        List<CmsActivityDraft> ActivityDraftList = activityDraftCustomMapper.getActivityDraftListByUserId(activityId, userId);
        for (CmsActivityDraft cmsActivityDraft : ActivityDraftList) {

            switch (cmsActivityDraft.getPresentType()) {
                case PresentType.COUPON:
                    customDrafts.addAll(activityDraftCustomMapper.getActivityCouponDraftListByUserId(activityId, userId, PresentType.COUPON));
                    break;
                default:
                    break;
            }
        }

        return customDrafts;
    }

    @Override
    public int getActivityDraftCount(String activityId, String userId) {
        return activityDraftCustomMapper.getActivityDraftCount(activityId, userId);
    }

    @Override
    public CmsActivityCustomDraft doActivityDraftProcess(String activityId, String userId) {
        //抽奖业务逻辑

        boolean trickOrTreat = true;


        if (trickOrTreat) {
            //选择奖品
            CmsActivityConfig couponConfig = activityConfigMapper.selectByPrimaryKey(activityId);
            String presentType = couponConfig.getDraftPresentType();
            switch (presentType) {
                case PresentType.COUPON:
                    //查询活动举办机构
                    CmsActivity activity = activityMapper.selectByPrimaryKey(activityId);
                    List<CmsCoupon> coupons = couponCustomMapper.getCouponList(activity.getOrgId());
                    //查询各优惠券库存
                    int initStockNo = 0;
                    String maxStockCouponId = "";
                    for (CmsCoupon item : coupons) {

                        int stockNo = batchItemCustomMapper.getCouponBatchItemStockByCouponId(item.getCouponId());
                        if (stockNo >= initStockNo) {
                            initStockNo = stockNo;
                            maxStockCouponId = item.getCouponId();
                        }
                    }
                    //如果优惠券已发放完毕
                    if (StringUtils.isEmpty(maxStockCouponId)) {
                        return null;
                    }
                    CmsCouponBatchItem couponItem = batchItemCustomMapper.selectNotArrangedCouponItemByCouponId(maxStockCouponId);
                    //如果优惠券不存在
                    if (couponItem.equals(null)) {
                        return null;
                    }
                    //乐观锁version字段
                    int resultRow = batchItemCustomMapper.giveCouponToUser(userId, couponItem.getCouponItemId(), couponItem.getVersion());
                    if (resultRow > 0) {
                        //查询用户抽奖记录
                        CmsActivityDraft unDraftedRecord = activityDraftCustomMapper.getActivityUnDraftedRecordByUserId(activityId, userId);

                        //更新奖品信息
                        if (unDraftedRecord != null ) {
                            CmsActivityDraft activityDraft = new CmsActivityDraft();
                            activityDraft.setDraftId(unDraftedRecord.getDraftId());
                            activityDraft.setDraftStatus(DRAFTED);
                            activityDraft.setDraftTime(new Date());
                            activityDraft.setUserId(userId);
                            activityDraft.setPresentId(couponItem.getCouponItemId());
                            activityDraft.setPresentType(PresentType.COUPON);
                            activityDrafMapper.updateByPrimaryKeySelective(activityDraft);
                            CmsActivityCustomDraft activityCustomDraft = activityDraftCustomMapper.getActivityCouponDraftByDraftId(unDraftedRecord.getDraftId());
                            return activityCustomDraft;
                        }
                    }

                    break;
                default:
                    break;

            }

        }


        return null;
    }

    @Override
    public CmsActivityDraft addActivityDraft(String activityId, String userId) {
        CmsActivityDraft draft =new CmsActivityDraft();
        draft.setDraftId(UUIDUtils.getUUID());
        draft.setActivityId(activityId);
        draft.setUserId(userId);
        draft.setDraftStatus(UNDRAFTED);
        draft.setCreateTime(new Date());
        activityDrafMapper.insertSelective(draft);
        return draft;
    }


    public boolean doTrickOrTreat() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
