package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import static com.linkpets.util.DateUtils.getFormatDateTime;

@Data
public class CmsActivity {

    private String activityId;

    private String activityName;

    private String introduction;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String orgId;

    private Date createTime;

    private Integer isActive;

    private Integer isValid;

    public CmsActivity() {
    }

    public CmsActivity(String activityId, String activityName, String introduction, Date startTime, Date endTime, String orgId, Date createTime, Integer isActive, Integer isValid) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.introduction = introduction;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orgId = orgId;
        this.createTime = createTime;
        this.isActive = isActive;
        this.isValid = isValid;
    }

    public CmsActivity(String activityId, String activityName, String introduction, String startTime, String endTime, String orgId, Date createTime) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.introduction = introduction;
        this.startTime = getFormatDateTime(startTime);
        this.endTime = getFormatDateTime(endTime);
        this.orgId = orgId;
        this.createTime = createTime;
    }


}