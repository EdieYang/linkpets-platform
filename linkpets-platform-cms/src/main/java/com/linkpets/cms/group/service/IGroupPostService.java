package com.linkpets.cms.group.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.respEntity.RespGroupPost;
import com.linkpets.core.model.CmsGroupPost;
import com.linkpets.core.model.CmsGroupPostImg;

import java.util.List;

/**
 * @Author Edie
 */
public interface IGroupPostService {


    /**
     * 分页获取圈子发帖列表
     *
     * @param groupId
     * @param isValid
     * @param nickName
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RespGroupPost> getGroupPostPage(String groupId, Integer isValid, String nickName, Integer pageNum, Integer pageSize);


    /**
     * 圈子发帖
     *
     * @param cmsGroupPost
     * @param postImgList
     * @return
     */
    String crtGroupPost(CmsGroupPost cmsGroupPost, List<CmsGroupPostImg> postImgList);

    /**
     * 更新圈子发帖
     *
     * @param cmsGroupPost
     * @param postImgList
     */
    void uptGroupPost(CmsGroupPost cmsGroupPost, List<CmsGroupPostImg> postImgList);

    /**
     * 删除圈子发帖
     *
     * @param postId
     * @param memo
     */
    void delGroupPost(String postId, String memo);

    /**
     * 点赞或取消点赞帖子
     * @param postId
     * @param userId
     * @param type（0：取消点赞 1：点赞）
     */
    void crtGroupPostLike(String postId, String userId, String type);


}
