package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsGroupActivity;
import lombok.Data;

@Data
public class RespGroupActivity extends CmsGroupActivity {

    /**
     * 活动发布状态
     */
    private String activityStatus;

    /**
     * 是否报名参加
     */
    private Integer hasRegistered;

}
