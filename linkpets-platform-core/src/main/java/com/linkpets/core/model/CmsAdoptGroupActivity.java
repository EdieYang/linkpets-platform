package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CmsAdoptGroupActivity {
    private String id;

    private String groupId;

    private String activityType;

    private String activityTitle;

    private String activityBanner;

    private String activityImg;

    private String activityArea;

    private String activityAddress;

    private String activityContent;

    private String activityShouldVerify;

    private String activityShouldQuestionnaire;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityEndTime;

    private Integer activityIsFree;

    private Integer activityCost;

    private String customerSupport;

    private Integer isActive;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityBanner() {
        return activityBanner;
    }

    public void setActivityBanner(String activityBanner) {
        this.activityBanner = activityBanner;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityShouldVerify() {
        return activityShouldVerify;
    }

    public void setActivityShouldVerify(String activityShouldVerify) {
        this.activityShouldVerify = activityShouldVerify;
    }

    public String getActivityShouldQuestionnaire() {
        return activityShouldQuestionnaire;
    }

    public void setActivityShouldQuestionnaire(String activityShouldQuestionnaire) {
        this.activityShouldQuestionnaire = activityShouldQuestionnaire;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getActivityIsFree() {
        return activityIsFree;
    }

    public void setActivityIsFree(Integer activityIsFree) {
        this.activityIsFree = activityIsFree;
    }

    public Integer getActivityCost() {
        return activityCost;
    }

    public void setActivityCost(Integer activityCost) {
        this.activityCost = activityCost;
    }

    public String getCustomerSupport() {
        return customerSupport;
    }

    public void setCustomerSupport(String customerSupport) {
        this.customerSupport = customerSupport;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}