package com.linkpets.cms.aprilfool.service.impl;

import com.linkpets.cms.aprilfool.service.IActivityDraftService;
import com.linkpets.core.dao.*;
import com.linkpets.core.model.*;
import com.linkpets.enums.PresentType;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author SteveYang
 * @date 2019/3/26
 */

@Service
public class ActivityDraftServiceImpl implements IActivityDraftService {

    private static final int DRAFTED = 1;
    private static final int UNDRAFTED = 0;


    @Resource
    private CmsActivityDraftMapper activityDrafMapper;

    @Resource
    private CmsActivityConfigMapper activityConfigMapper;

    @Resource
    private CmsCouponBatchItemMapper batchItemMapper;

    @Resource
    private CmsCouponMapper couponMapper;

    @Resource
    private CmsActivityMapper activityMapper;

    @Override
    public int getActivityDraftExistNoByUserId(String activityId, String userId) {
        return activityDrafMapper.getActivityDraftExistNoByUserId(activityId, userId);
    }

    @Override
    public List<CmsActivityDraft> getActivityDraftListByUserId(String activityId, String userId) {
        List<CmsActivityDraft> customDrafts = new ArrayList<>();
        List<CmsActivityDraft> ActivityDraftList = activityDrafMapper.getActivityDraftListByUserId(activityId, userId);
        for (CmsActivityDraft cmsActivityDraft : ActivityDraftList) {

            switch (cmsActivityDraft.getPresentType()) {
                case PresentType.COUPON:
                    customDrafts.addAll(activityDrafMapper.getActivityCouponDraftListByUserId(activityId, userId, PresentType.COUPON));
                    break;
                default:
                    break;
            }
        }

        return customDrafts;
    }

    @Override
    public int getActivityDraftCount(String activityId, String userId) {
        return activityDrafMapper.getActivityDraftCount(activityId, userId);
    }

    @Override
    public CmsActivityDraft doActivityDraftProcess(String activityId, String userId) {
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
                    List<CmsCoupon> coupons = couponMapper.getCouponList(activity.getOrgId());
                    //查询各优惠券库存
                    int initStockNo = 0;
                    String maxStockCouponId = "";
                    for (CmsCoupon item : coupons) {

                        int stockNo = batchItemMapper.getCouponBatchItemStockByCouponId(item.getCouponId());
                        if (stockNo >= initStockNo) {
                            initStockNo = stockNo;
                            maxStockCouponId = item.getCouponId();
                        }
                    }
                    //如果优惠券已发放完毕
                    if (StringUtils.isEmpty(maxStockCouponId)) {
                        return null;
                    }
                    CmsCouponBatchItem couponItem = batchItemMapper.selectNotArrangedCouponItemByCouponId(maxStockCouponId);
                    //如果优惠券不存在
                    if (couponItem.equals(null)) {
                        return null;
                    }
                    //乐观锁version字段
                    int resultRow = batchItemMapper.giveCouponToUser(userId, couponItem.getCouponItemId(), couponItem.getVersion());
                    if (resultRow > 0) {
                        //查询用户抽奖记录
                        CmsActivityDraft unDraftedRecord = activityDrafMapper.getActivityUnDraftedRecordByUserId(activityId, userId);

                        //更新奖品信息
                        if (unDraftedRecord != null) {
                            CmsActivityDraft activityDraft = new CmsActivityDraft();
                            activityDraft.setDraftId(unDraftedRecord.getDraftId());
                            activityDraft.setDraftStatus(DRAFTED);
                            activityDraft.setDraftTime(new Date());
                            activityDraft.setUserId(userId);
                            activityDraft.setPresentId(couponItem.getCouponItemId());
                            activityDraft.setPresentType(PresentType.COUPON);
                            activityDrafMapper.updateByPrimaryKeySelective(activityDraft);
                            CmsActivityDraft activityCustomDraft = activityDrafMapper.getActivityCouponDraftByDraftId(unDraftedRecord.getDraftId());
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
        CmsActivityDraft draft = new CmsActivityDraft();
        draft.setDraftId(UUIDUtils.getId());
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
