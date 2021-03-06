package com.linkpets.core.dao;

import com.linkpets.core.model.CmsUserLogin;

public interface CmsUserLoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_user_login
     *
     * @mbggenerated Sat May 18 20:40:12 CST 2019
     */
    int deleteByPrimaryKey(String loginId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_user_login
     *
     * @mbggenerated Sat May 18 20:40:12 CST 2019
     */
    int insert(CmsUserLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_user_login
     *
     * @mbggenerated Sat May 18 20:40:12 CST 2019
     */
    int insertSelective(CmsUserLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_user_login
     *
     * @mbggenerated Sat May 18 20:40:12 CST 2019
     */
    CmsUserLogin selectByPrimaryKey(String loginId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_user_login
     *
     * @mbggenerated Sat May 18 20:40:12 CST 2019
     */
    int updateByPrimaryKeySelective(CmsUserLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_user_login
     *
     * @mbggenerated Sat May 18 20:40:12 CST 2019
     */
    int updateByPrimaryKey(CmsUserLogin record);

    /**
     * 获取用户登录信息
     *
     * @param userId
     * @return
     */
    CmsUserLogin getLastLoginTime(String userId);

    /**
     * 获取当天用户登录数
     *
     * @param syncDate
     * @return
     */
    int getLoginCount(String syncDate);
}