package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CmsAdoptGroupActivity {
    private String id;

    @ApiModelProperty(value = "圈子Id")
    private String groupId;

    @ApiModelProperty(value = "活动类型 1 -线上活动  2-线下活动")
    private String activityType;

    @ApiModelProperty(value = "活动标题 ")
    private String activityTitle;

    @ApiModelProperty(value = "活动banner图 ")
    private String activityBanner;

    @ApiModelProperty(value = "活动市区所在地 ")
    private String activityArea;

    @ApiModelProperty(value = "活动地址 ")
    private String activityAddress;

    @ApiModelProperty(value = "活动banner图 ")
    private String activityContent;

    @ApiModelProperty(value = "是否需要实名认证 0-否 1-是 ")
    private String activityShouldVerify;

    @ApiModelProperty(value = "是否需要填写问卷 0-否 1-是 ")
    private String activityShouldQuestionnaire;

    @ApiModelProperty(value = "问卷id ")
    private String questionnaireId;

    @ApiModelProperty(value = "活动报名开始时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityRegisterStartTime;

    @ApiModelProperty(value = "活动报名结束时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityRegisterEndTime;

    @ApiModelProperty(value = "活动整体开始时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityStartTime;

    @ApiModelProperty(value = "活动整体结束时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityEndTime;

    @ApiModelProperty(value = "活动参加时间范围 逗号分隔字符串 ")
    private String activityPickTime;

    @ApiModelProperty(value = "是否收费费 0-免费 1-收费 ")
    private Integer activityIsFree;

    @ApiModelProperty(value = "活动价格(免费 此价格为积分) ")
    private Integer activityCost;

    @ApiModelProperty(value = "活动获取积分数 ")
    private Integer activityPoint;

    @ApiModelProperty(value = "活动客服微信号 ")
    private String customerSupport;

    @ApiModelProperty(value = "活动上线 0：下架 1：上线")
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

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Date getActivityRegisterStartTime() {
        return activityRegisterStartTime;
    }

    public void setActivityRegisterStartTime(Date activityRegisterStartTime) {
        this.activityRegisterStartTime = activityRegisterStartTime;
    }

    public Date getActivityRegisterEndTime() {
        return activityRegisterEndTime;
    }

    public void setActivityRegisterEndTime(Date activityRegisterEndTime) {
        this.activityRegisterEndTime = activityRegisterEndTime;
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

    public String getActivityPickTime() {
        return activityPickTime;
    }

    public void setActivityPickTime(String activityPickTime) {
        this.activityPickTime = activityPickTime;
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

    public Integer getActivityPoint() {
        return activityPoint;
    }

    public void setActivityPoint(Integer activityPoint) {
        this.activityPoint = activityPoint;
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