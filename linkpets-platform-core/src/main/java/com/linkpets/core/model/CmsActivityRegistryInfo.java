package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsActivityRegistryInfo {

    private String infoId;

    private String registerName;

    private String registerWx;

    private String registerPhone;

    private String infoContentFirst;

    private String infoContentSecond;

    private String infoContentThird;

    private String infoContentForth;

    private String activityId;

    private String userId;

    private Integer state;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}