package com.linkpets.cms.coupon.service;

import com.linkpets.core.model.CmsCouponBatch;

import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface ICmsCouponBatchService {

    CmsCouponBatch getCouponBatch(String batchNo);

    Map<String, Object> getCouponBatchList(String couponId, String activityId, String effectiveStart, String effectiveEnd, int pageNo, int pageSize);

    String createCouponBatch(String couponId, String activityId, String effectiveStart, String effectiveEnd);

    int createCouponBatchItems(String batchNo,int couponAmount);

    Map<String,Object> getCouponBatchItemsByBatchNo(String batchNo,int status,int pageNo,int pageSize);

}
