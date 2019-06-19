package com.linkpets.cms.coupon.service;

import com.linkpets.core.model.CmsCoupon;

import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface ICmsCouponService {

    String createCoupon(String couponName, String couponEnName, int couponType, String couponRule, String couponRemind, String orgId);

    CmsCoupon getCouponByCouponId(String couponId);

    Map<String, Object> getCouponList(String orgId, int pageNo, int pageSize);
}
