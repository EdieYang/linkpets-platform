package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.core.respEntity.RespGroupPost;
import com.linkpets.cms.adopt.service.IGroupPostService;
import com.linkpets.core.dao.CmsAdoptGroupPostImgMapper;
import com.linkpets.core.dao.CmsAdoptGroupPostMapper;
import com.linkpets.core.model.CmsAdoptGroupPost;
import com.linkpets.core.model.CmsAdoptGroupPostImg;
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
    private CmsAdoptGroupPostMapper groupPostMapper;

    @Resource
    private CmsAdoptGroupPostImgMapper groupPostImgMapper;


    @Override
    public PageInfo<RespGroupPost> getGroupPostPage(String groupId, Integer isValid, String nickName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RespGroupPost> postList = groupPostMapper.getGroupPostPage(groupId,isValid,nickName);
        return new PageInfo<>(postList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtGroupPost(CmsAdoptGroupPost cmsAdoptGroupPost, List<CmsAdoptGroupPostImg> postImgList) {
        String postId = UUIDUtils.getUUID();
        cmsAdoptGroupPost.setPostId(postId);
        cmsAdoptGroupPost.setCreateDate(new Date());
        groupPostMapper.insertSelective(cmsAdoptGroupPost);

        postImgList.forEach(postImg -> {
            postImg.setId(UUIDUtils.getUUID());
            postImg.setPostId(postId);
            postImg.setCreateDate(new Date());
            groupPostImgMapper.insertSelective(postImg);
        });

        return postId;
    }

    @Override
    public void uptGroupPost(CmsAdoptGroupPost cmsAdoptGroupPost, List<CmsAdoptGroupPostImg> postImgList) {
        CmsAdoptGroupPost groupPost = groupPostMapper.selectByPrimaryKey(cmsAdoptGroupPost.getPostId());
        if (groupPost != null) {
            String postId = groupPost.getPostId();
            groupPostMapper.updateByPrimaryKeySelective(cmsAdoptGroupPost);
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
    public void delGroupPost(String postId,String memo) {
        CmsAdoptGroupPost groupPost = new CmsAdoptGroupPost();
        groupPost.setPostId(postId);
        groupPost.setMemo(memo);
        groupPost.setIsValid(0);
        groupPostMapper.updateByPrimaryKeySelective(groupPost);
        groupPostImgMapper.deleteByPostId(postId);
    }
}
