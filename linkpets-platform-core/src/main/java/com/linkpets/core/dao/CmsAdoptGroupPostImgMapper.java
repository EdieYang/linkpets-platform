package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptGroupPostImg;

public interface CmsAdoptGroupPostImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsAdoptGroupPostImg record);

    int insertSelective(CmsAdoptGroupPostImg record);

    CmsAdoptGroupPostImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsAdoptGroupPostImg record);

    int updateByPrimaryKey(CmsAdoptGroupPostImg record);

    void deleteByPostId(String postId);
}