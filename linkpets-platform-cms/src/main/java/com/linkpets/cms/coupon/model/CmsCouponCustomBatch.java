package com.linkpets.cms.coupon.model;

import com.linkpets.core.model.CmsCouponBatch;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public class CmsCouponCustomBatch extends CmsCouponBatch {

    private String couponName;
    private String couponEnName;
    private String couponType;
    private String couponRule;
    private String couponRemind;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponEnName() {
        return couponEnName;
    }

    public void setCouponEnName(String couponEnName) {
        this.couponEnName = couponEnName;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponRule() {
        return couponRule;
    }

    public void setCouponRule(String couponRule) {
        this.couponRule = couponRule;
    }

    public String getCouponRemind() {
        return couponRemind;
    }

    public void setCouponRemind(String couponRemind) {
        this.couponRemind = couponRemind;
    }
}
