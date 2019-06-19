package com.linkpets.cms.lost.service.impl;

import com.github.pagehelper.PageHelper;
import com.linkpets.cms.lost.dao.CmsLostPostCustomMapper;
import com.linkpets.cms.lost.model.PostsInfo;
import com.linkpets.cms.lost.service.ILostPostService;
import com.linkpets.core.dao.CmsLostPostMapper;
import com.linkpets.core.model.CmsLostPost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class lostPostServiceImpl implements ILostPostService {

    @Resource
    private CmsLostPostMapper lostPostMapper;

    @Resource
    private CmsLostPostCustomMapper cmsLostPostCustomMapper;

    @Override
    public void updatePost(CmsLostPost lostPost) {
        lostPostMapper.updateByPrimaryKeySelective(lostPost);
    }

    @Override
    public void insertPost(CmsLostPost lostPost) {
        lostPostMapper.insertSelective(lostPost);
    }

    @Override
    public List<PostsInfo> getLostPostsList(int pageNo, int pageSize, String city, int dayLimit) {
        PageHelper.startPage(pageNo, pageSize);
        return cmsLostPostCustomMapper.getLostPostsList(city, dayLimit);
    }

    @Override
    public PostsInfo getLostPost(String postId) {
        return cmsLostPostCustomMapper.getLostPost(postId);
    }
}
