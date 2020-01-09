package com.linkpets.core.respEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkpets.core.model.CmsGroupPostImg;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 圈子帖子分页返回实体
 *
 * @author edie
 */
@Data
public class RespGroupPost {

    private String postId;

    private String groupId;

    private String userId;

    private String nickName;

    private String portrait;

    private String postContent;

    private String memo;

    private String wxAccount;

    private Integer likeAmount;

    private Integer isFollowed;

    private Integer isValid;

    private List<CmsGroupPostImg> groupPostImgList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

}
