package com.linkpets.core.dao;

import com.linkpets.core.model.AdoptOrgStatistic;
import com.linkpets.core.model.CmsAdoptOrg;
import com.linkpets.core.model.CmsAdoptOrgActivity;
import com.linkpets.core.model.CmsAdoptOrgGallery;

import java.util.List;

public interface CmsAdoptOrgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_org
     *
     * @mbggenerated Fri Aug 16 17:19:34 CST 2019
     */
    int deleteByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_org
     *
     * @mbggenerated Fri Aug 16 17:19:34 CST 2019
     */
    int insert(CmsAdoptOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_org
     *
     * @mbggenerated Fri Aug 16 17:19:34 CST 2019
     */
    int insertSelective(CmsAdoptOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_org
     *
     * @mbggenerated Fri Aug 16 17:19:34 CST 2019
     */
    CmsAdoptOrg selectByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_org
     *
     * @mbggenerated Fri Aug 16 17:19:34 CST 2019
     */
    int updateByPrimaryKeySelective(CmsAdoptOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_org
     *
     * @mbggenerated Fri Aug 16 17:19:34 CST 2019
     */
    int updateByPrimaryKey(CmsAdoptOrg record);


    List<CmsAdoptOrg> getAdoptOrgList();

    AdoptOrgStatistic getAdoptOrgStatistic(String orgId);

    List<CmsAdoptOrgGallery> getAdoptGalleryList(String orgId);

    List<CmsAdoptOrgActivity> getAdoptActivityList(String orgId);
}