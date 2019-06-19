package com.linkpets.cms.coupon.dao;

import com.linkpets.core.model.CmsCoupon;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public interface CmsCouponCustomMapper {

    List<CmsCoupon> getCouponList(String orgId);
}
