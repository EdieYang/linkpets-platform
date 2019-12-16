package com.linkpets.cms.adopt.reqEntity;


import com.linkpets.core.model.CmsAdoptGroupPost;
import com.linkpets.core.model.CmsAdoptGroupPostImg;
import lombok.Data;

import java.util.List;

/**
 * 圈子发帖请求
 * @author edie
 */
@Data
public class GroupPostReq {


    private CmsAdoptGroupPost cmsAdoptGroupPost;

    private List<CmsAdoptGroupPostImg> cmsAdoptGroupPostImgList;

}
