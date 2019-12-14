package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptGroupPost;
import com.linkpets.core.model.CmsAdoptGroupPostImg;

import java.util.List;

/**
 * @Author Edie
 */
public interface IGroupPostService {

    /**
     * 圈子发帖
     *
     * @param cmsAdoptGroupPost
     * @param postImgList
     * @return
     */
    String crtGroupPost(CmsAdoptGroupPost cmsAdoptGroupPost, List<CmsAdoptGroupPostImg> postImgList);

    /**
     * 更新圈子发帖
     *
     * @param cmsAdoptGroupPost
     * @param postImgList
     */
    void uptGroupPost(CmsAdoptGroupPost cmsAdoptGroupPost, List<CmsAdoptGroupPostImg> postImgList);

}
