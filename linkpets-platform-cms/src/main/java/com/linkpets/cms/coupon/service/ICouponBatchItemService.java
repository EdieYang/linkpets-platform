package com.linkpets.cms.coupon.service;

import com.linkpets.core.model.CmsCouponBatchItem;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface ICouponBatchItemService {

    List<CmsCouponBatchItem> getCouponBatchItemListByUserId(String userId, int status);

    CmsCouponBatchItem getCouponBatchItemByCouponItemId(String couponItemId);

    CmsCouponBatchItem updateCouponBatchItemByCouponItemId(String couponItemId, String certifyChainId);

    List<CmsCouponBatchItem> getCouponBatchItemListByChainId(String certifyChainId);

}
