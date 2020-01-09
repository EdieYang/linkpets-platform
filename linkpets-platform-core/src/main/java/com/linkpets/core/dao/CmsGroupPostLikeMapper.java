package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupPostLike;

public interface CmsGroupPostLikeMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsGroupPostLike record);

    int insertSelective(CmsGroupPostLike record);

    CmsGroupPostLike selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsGroupPostLike record);

    int updateByPrimaryKey(CmsGroupPostLike record);

    CmsGroupPostLike getPostLikeByPostIdAndUserId(String postId, String userId);
}