package com.linkpets.cms.coupon.service;

import com.linkpets.cms.coupon.model.CmsCouponBatchCustomItem;
import com.linkpets.core.model.CmsCouponBatch;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface ICouponBatchItemService {

    List<CmsCouponBatchCustomItem> getCouponBatchItemListByUserId(String userId,int status);

    CmsCouponBatchCustomItem getCouponBatchItemByCouponItemId(String couponItemId);

    CmsCouponBatchCustomItem updateCouponBatchItemByCouponItemId(String couponItemId,String certifyChainId);

    List<CmsCouponBatchCustomItem> getCouponBatchItemListByChainId(String certifyChainId);

}
