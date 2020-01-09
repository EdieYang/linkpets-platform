package com.linkpets.cms.group.reqEntity;


import com.linkpets.core.model.CmsGroupPost;
import com.linkpets.core.model.CmsGroupPostImg;
import lombok.Data;

import java.util.List;

/**
 * 圈子发帖请求
 * @author edie
 */
@Data
public class GroupPostReq {


    private CmsGroupPost cmsGroupPost;

    private List<CmsGroupPostImg> cmsGroupPostImgList;

}
