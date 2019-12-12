package com.linkpets.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CmsBanner {

    @ApiModelProperty(value = "id")
    private String bannerId;

    @ApiModelProperty(value = "BANNER类型 -ADOPT：公益平台")
    private String activityId;

    @ApiModelProperty(value = "BANNER图片地址")
    private String bannerImgUrl;

    @ApiModelProperty(value = "BANNER跳转URL")
    private String bannerRedirectUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private Date createTime;

    private Integer isValid;

}