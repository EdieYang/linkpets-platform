package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CmsGroupActivity {
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

    @ApiModelProperty(value = "活动报名人数限制 ")
    private Integer registerLimit;

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

}