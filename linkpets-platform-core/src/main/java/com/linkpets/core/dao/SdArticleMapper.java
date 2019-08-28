package com.linkpets.core.dao;

import com.linkpets.core.model.SdArticle;

public interface SdArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_article
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_article
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    int insert(SdArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_article
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    int insertSelective(SdArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_article
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    SdArticle selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_article
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    int updateByPrimaryKeySelective(SdArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sd_article
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    int updateByPrimaryKey(SdArticle record);

    /**
     * 获取百科文章详情
     *
     * @param catalogListId
     * @param catalogId
     * @param type
     * @return
     */
    SdArticle selectByCatalogId(String catalogListId, String catalogId, String type);
}