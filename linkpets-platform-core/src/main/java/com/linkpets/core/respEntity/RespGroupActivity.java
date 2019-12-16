package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsAdoptGroupActivity;
import lombok.Data;

@Data
public class RespGroupActivity extends CmsAdoptGroupActivity {

    /**
     * 活动发布状态
     */
    private String activityStatus;

}
