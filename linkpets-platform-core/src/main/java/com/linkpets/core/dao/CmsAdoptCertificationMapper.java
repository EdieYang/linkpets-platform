package com.linkpets.core.dao;

import com.linkpets.core.model.CmsAdoptCertification;

public interface CmsAdoptCertificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_certification
     *
     * @mbggenerated Sat May 25 12:04:06 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_certification
     *
     * @mbggenerated Sat May 25 12:04:06 CST 2019
     */
    int insert(CmsAdoptCertification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_certification
     *
     * @mbggenerated Sat May 25 12:04:06 CST 2019
     */
    int insertSelective(CmsAdoptCertification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_certification
     *
     * @mbggenerated Sat May 25 12:04:06 CST 2019
     */
    CmsAdoptCertification selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_certification
     *
     * @mbggenerated Sat May 25 12:04:06 CST 2019
     */
    int updateByPrimaryKeySelective(CmsAdoptCertification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_certification
     *
     * @mbggenerated Sat May 25 12:04:06 CST 2019
     */
    int updateByPrimaryKey(CmsAdoptCertification record);


    CmsAdoptCertification getUserCertification(String userId);
}