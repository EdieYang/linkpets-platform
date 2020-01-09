package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupPostImg;

public interface CmsAdoptGroupPostImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsGroupPostImg record);

    int insertSelective(CmsGroupPostImg record);

    CmsGroupPostImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsGroupPostImg record);

    int updateByPrimaryKey(CmsGroupPostImg record);

    void deleteByPostId(String postId);
}