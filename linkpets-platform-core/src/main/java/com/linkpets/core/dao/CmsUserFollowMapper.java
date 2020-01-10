package com.linkpets.core.dao;

import com.linkpets.core.model.CmsUserFollow;
import org.apache.ibatis.annotations.Param;

public interface CmsUserFollowMapper {
    int deleteByPrimaryKey(String id);

    int insert(CmsUserFollow record);

    int insertSelective(CmsUserFollow record);

    CmsUserFollow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsUserFollow record);

    int updateByPrimaryKey(CmsUserFollow record);

    /**
     * 取消关注
     *
     * @param userId
     * @param followBy
     */
    void delFollow(@Param("userId") String userId, @Param("followBy") String followBy);
}