package com.linkpets.cms.aprilfool.model;

import com.linkpets.core.model.CmsActivityDraft;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
public class CmsActivityCustomDraft extends CmsActivityDraft {

    private String presentName;

    private String publishOrgName;

    private int couponType;

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public String getPublishOrgName() {
        return publishOrgName;
    }

    public void setPublishOrgName(String publishOrgName) {
        this.publishOrgName = publishOrgName;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }
}
