package com.linkpets.cms.coupon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkpets.core.model.CmsCouponBatchItem;

import java.util.Date;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public class CmsCouponBatchCustomItem  extends CmsCouponBatchItem {

    private Date effectiveStart;

    private Date effectiveEnd;

    private String activityId;

    private String activityName;

    private String couponName;

    private String couponEnName;

    private String couponType;

    private String couponRule;

    private String couponRemind;

    private String orgId;

    private String orgName;

    private String orgLogo;

    private String userName;

    private String userPortrait;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEffectiveStart() {
        return effectiveStart;
    }

    public void setEffectiveStart(Date effectiveStart) {
        this.effectiveStart = effectiveStart;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEffectiveEnd() {
        return effectiveEnd;
    }

    public void setEffectiveEnd(Date effectiveEnd) {
        this.effectiveEnd = effectiveEnd;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
