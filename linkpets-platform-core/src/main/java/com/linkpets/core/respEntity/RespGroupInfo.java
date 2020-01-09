package com.linkpets.core.respEntity;

import com.linkpets.core.model.CmsGroup;
import lombok.Data;

/**
 * 圈子详情
 *
 * @author edie
 */
@Data
public class RespGroupInfo extends CmsGroup {

    /**
     * 粉丝数
     */
    private String fansCount;

    /**
     * 内容数
     */
    private String contentCount;

    /**
     * 是否已关注圈子（0：否 1：是）
     */
    private Integer isFollowed;

}
