package com.linkpets.cms.group.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.group.service.IGroupPostService;
import com.linkpets.core.dao.CmsGroupPostImgMapper;
import com.linkpets.core.dao.CmsGroupPostLikeMapper;
import com.linkpets.core.dao.CmsGroupPostMapper;
import com.linkpets.core.model.CmsGroupPost;
import com.linkpets.core.model.CmsGroupPostImg;
import com.linkpets.core.model.CmsGroupPostLike;
import com.linkpets.core.respEntity.RespGroupPost;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Edie
 */
@Service
public class GroupPostServiceImpl implements IGroupPostService {

    @Resource
    private CmsGroupPostMapper groupPostMapper;

    @Resource
    private CmsGroupPostImgMapper groupPostImgMapper;

    @Resource
    private CmsGroupPostLikeMapper groupPostLikeMapper;


    @Override
    public PageInfo<RespGroupPost> getGroupPostPage(String groupId, Integer isValid, String nickName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupPost> postList = groupPostMapper.getGroupPostPage(groupId, isValid, nickName);
        return new PageInfo<>(postList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtGroupPost(CmsGroupPost cmsGroupPost, List<CmsGroupPostImg> postImgList) {
        String postId = UUIDUtils.getId();
        cmsGroupPost.setPostId(postId);
        cmsGroupPost.setCreateDate(new Date());
        groupPostMapper.insertSelective(cmsGroupPost);

        postImgList.forEach(postImg -> {
            postImg.setId(UUIDUtils.getId());
            postImg.setPostId(postId);
            postImg.setCreateDate(new Date());
            groupPostImgMapper.insertSelective(postImg);
        });

        return postId;
    }

    @Override
    public void uptGroupPost(CmsGroupPost cmsGroupPost, List<CmsGroupPostImg> postImgList) {
        CmsGroupPost groupPost = groupPostMapper.selectByPrimaryKey(cmsGroupPost.getPostId());
        if (groupPost != null) {
            String postId = groupPost.getPostId();
            groupPostMapper.updateByPrimaryKeySelective(cmsGroupPost);
            groupPostImgMapper.deleteByPostId(postId);
            postImgList.forEach(postImg -> {
                postImg.setPostId(postId);
                postImg.setCreateDate(new Date());
                groupPostImgMapper.insertSelective(postImg);
            });
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delGroupPost(String postId, String memo) {
        CmsGroupPost groupPost = new CmsGroupPost();
        groupPost.setPostId(postId);
        groupPost.setMemo(memo);
        groupPost.setIsValid(0);
        groupPostMapper.updateByPrimaryKeySelective(groupPost);
        groupPostImgMapper.deleteByPostId(postId);
    }

    @Override
    public void crtGroupPostLike(String postId, String userId, String type) {

        CmsGroupPostLike groupPostLike = groupPostLikeMapper.getPostLikeByPostIdAndUserId(postId, userId);
        if (type.equals("0")) {
            if (groupPostLike != null) {
                groupPostLike.setIsValid(0);
                groupPostLikeMapper.updateByPrimaryKey(groupPostLike);
            }
        }
        if (type.equals("1")) {
            if (groupPostLike == null) {
                groupPostLike = new CmsGroupPostLike();
                groupPostLike.setId(UUIDUtils.getId());
                groupPostLike.setPostId(postId);
                groupPostLike.setUserId(userId);
                groupPostLike.setCreateDate(new Date());
                groupPostLikeMapper.insertSelective(groupPostLike);
            } else if (groupPostLike.getIsValid() == 0) {
                groupPostLike.setIsValid(1);
                groupPostLikeMapper.updateByPrimaryKey(groupPostLike);
            }
        }
    }
}
