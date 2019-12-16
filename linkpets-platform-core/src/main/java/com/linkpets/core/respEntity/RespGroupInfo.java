package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsAdoptGroup;
import lombok.Data;

/**
 * 圈子详情
 *
 * @author edie
 */
@Data
public class RespGroupInfo extends CmsAdoptGroup {

    /**
     * 粉丝数
     */
    private String fansCount;

    /**
     * 内容数
     */
    private String contentCount;

}
