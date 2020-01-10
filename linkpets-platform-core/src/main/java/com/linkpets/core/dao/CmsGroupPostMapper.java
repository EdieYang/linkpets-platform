package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupPost;
import com.linkpets.core.respEntity.RespGroupPost;

import java.util.List;

public interface CmsGroupPostMapper {
    int deleteByPrimaryKey(String postId);

    int insert(CmsGroupPost record);

    int insertSelective(CmsGroupPost record);

    CmsGroupPost selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(CmsGroupPost record);

    int updateByPrimaryKey(CmsGroupPost record);

    /**
     * 分页获取圈子发帖列表
     *
     * @param groupId
     * @return
     */
    List<RespGroupPost> getGroupPostPage(String groupId, Integer isValid, String nickName);
}