package com.linkpets.core.dao;

import org.apache.ibatis.annotations.Param;

import com.linkpets.core.model.CmsAdoptAttention;

public interface CmsAdoptAttentionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_attention
     *
     * @mbg.generated Tue May 21 21:19:55 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_attention
     *
     * @mbg.generated Tue May 21 21:19:55 GMT+08:00 2019
     */
    int insert(CmsAdoptAttention record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_attention
     *
     * @mbg.generated Tue May 21 21:19:55 GMT+08:00 2019
     */
    int insertSelective(CmsAdoptAttention record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_attention
     *
     * @mbg.generated Tue May 21 21:19:55 GMT+08:00 2019
     */
    CmsAdoptAttention selectByPrimaryKey(Integer pkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_attention
     *
     * @mbg.generated Tue May 21 21:19:55 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(CmsAdoptAttention record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_adopt_attention
     *
     * @mbg.generated Tue May 21 21:19:55 GMT+08:00 2019
     */
    int updateByPrimaryKey(CmsAdoptAttention record);

	void delAttention(@Param("userId") String userId, @Param("attentBy") String attentBy);
}