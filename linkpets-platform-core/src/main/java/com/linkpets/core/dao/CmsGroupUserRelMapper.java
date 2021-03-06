package com.linkpets.core.dao;

import com.linkpets.core.model.CmsGroupUserRel;

public interface CmsGroupUserRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_group_user_rel
     *
     * @mbg.generated Thu Dec 12 18:52:54 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_group_user_rel
     *
     * @mbg.generated Thu Dec 12 18:52:54 CST 2019
     */
    int insert(CmsGroupUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_group_user_rel
     *
     * @mbg.generated Thu Dec 12 18:52:54 CST 2019
     */
    int insertSelective(CmsGroupUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_group_user_rel
     *
     * @mbg.generated Thu Dec 12 18:52:54 CST 2019
     */
    CmsGroupUserRel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_group_user_rel
     *
     * @mbg.generated Thu Dec 12 18:52:54 CST 2019
     */
    int updateByPrimaryKeySelective(CmsGroupUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_group_user_rel
     *
     * @mbg.generated Thu Dec 12 18:52:54 CST 2019
     */
    int updateByPrimaryKey(CmsGroupUserRel record);


    CmsGroupUserRel selectByUserIdAndGroupId(String userId, String groupId);
}