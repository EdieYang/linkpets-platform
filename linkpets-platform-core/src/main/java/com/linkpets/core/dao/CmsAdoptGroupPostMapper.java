package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptGroupPost;
import com.linkpets.core.respEntity.RespGroupPost;

import java.util.List;

public interface CmsAdoptGroupPostMapper {
    int deleteByPrimaryKey(String postId);

    int insert(CmsAdoptGroupPost record);

    int insertSelective(CmsAdoptGroupPost record);

    CmsAdoptGroupPost selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(CmsAdoptGroupPost record);

    int updateByPrimaryKey(CmsAdoptGroupPost record);

    /**
     * 分页获取圈子发帖列表
     *
     * @param groupId
     * @return
     */
    List<RespGroupPost> getGroupPostPage(String groupId, Integer isValid, String nickName);
}