package com.linkpets.cms.lost.service;

import com.linkpets.cms.lost.model.PostsInfo;
import com.linkpets.core.model.CmsLostPost;

import java.util.List;

public interface ILostPostService {

    void updatePost(CmsLostPost lostPost);

    void insertPost(CmsLostPost lostPost);

    List<PostsInfo> getLostPostsList(int pageNo, int pageSize, String city, int dayLimit);

    PostsInfo getLostPost(String postId);

}
