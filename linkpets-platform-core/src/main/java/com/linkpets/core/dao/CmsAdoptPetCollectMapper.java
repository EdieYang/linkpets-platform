package com.linkpets.core.dao;

import org.apache.ibatis.annotations.Param;

import com.linkpets.core.model.CmsAdoptPetCollect;

import java.util.List;

public interface CmsAdoptPetCollectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_pet_collect
     *
     * @mbg.generated Tue May 21 21:21:03 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_pet_collect
     *
     * @mbg.generated Tue May 21 21:21:03 GMT+08:00 2019
     */
    int insert(CmsAdoptPetCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_pet_collect
     *
     * @mbg.generated Tue May 21 21:21:03 GMT+08:00 2019
     */
    int insertSelective(CmsAdoptPetCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_pet_collect
     *
     * @mbg.generated Tue May 21 21:21:03 GMT+08:00 2019
     */
    CmsAdoptPetCollect selectByPrimaryKey(Integer pkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_pet_collect
     *
     * @mbg.generated Tue May 21 21:21:03 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(CmsAdoptPetCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_pet_collect
     *
     * @mbg.generated Tue May 21 21:21:03 GMT+08:00 2019
     */
    int updateByPrimaryKey(CmsAdoptPetCollect record);

	void delCollect(@Param("userId") String userId, @Param("petId") String petId);

	List<CmsAdoptPetCollect> getCollectionRel(@Param("userId") String userId, @Param("petId") String petId);
}