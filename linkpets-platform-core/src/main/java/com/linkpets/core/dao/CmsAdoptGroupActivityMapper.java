package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptGroupActivity;

import java.util.List;

public interface CmsAdoptGroupActivityMapper {

    int deleteByPrimaryKey(String id);

    int insert(CmsAdoptGroupActivity record);

    int insertSelective(CmsAdoptGroupActivity record);

    CmsAdoptGroupActivity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsAdoptGroupActivity record);

    int updateByPrimaryKey(CmsAdoptGroupActivity record);

    /**
     * 查询圈子活动列表
     *
     * @param groupId
     * @param isActive
     * @return
     */
    List<CmsAdoptGroupActivity> getAdoptGroupActivityList(String groupId, Integer isActive);
}