package com.linkpets.cms.coupon.dao;

import com.linkpets.core.model.CmsCouponBatch;
import com.linkpets.core.model.CmsCouponBatchItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface CmsCouponBatchCustomMapper {

    List<CmsCouponBatch> getCmsCouponBatchList(@Param("couponId") String couponId, @Param("activityId") String activityId, @Param("effectiveStart") String effectiveStart, @Param("effectiveEnd") String effectiveEnd);

    int insertBatchCouponItem(List<CmsCouponBatchItem> items);
}
