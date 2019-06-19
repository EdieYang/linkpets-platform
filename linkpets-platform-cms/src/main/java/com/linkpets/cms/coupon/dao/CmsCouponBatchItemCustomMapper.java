package com.linkpets.cms.coupon.dao;

import com.linkpets.cms.coupon.model.CmsCouponBatchCustomItem;
import com.linkpets.core.model.CmsCouponBatchItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface CmsCouponBatchItemCustomMapper {

    List<CmsCouponBatchItem> getCmsCouponBatchItemList(@Param("batchNo") String batchNo, @Param("status") int status);


    List<CmsCouponBatchCustomItem> getCouponBatchItemListByUserId(@Param("userId") String userId, @Param("status") int status);

    CmsCouponBatchCustomItem getCouponBatchItemByCouponItemId(String couponItemId);

    int updateCouponBatchItemByCouponItemId(@Param("couponItemId")String couponItemId,@Param("certifyChainId") String certifyChainId);

    int getCouponBatchItemStockByCouponId(String couponId);

    CmsCouponBatchItem selectNotArrangedCouponItemByCouponId(String couponId);

    int giveCouponToUser(String userId,String couponItemId,int version);

    List<CmsCouponBatchCustomItem> getCouponBatchItemListByChainId(String certifyChainId);
}
