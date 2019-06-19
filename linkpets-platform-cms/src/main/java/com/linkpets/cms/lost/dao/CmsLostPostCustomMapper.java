package com.linkpets.cms.lost.dao;

import com.linkpets.cms.lost.model.PostsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsLostPostCustomMapper {

    List<PostsInfo> getLostPostsList(@Param("city") String city, @Param("dayLimit") int dayLimit);

    PostsInfo getLostPost(String postId);
}
