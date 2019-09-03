package com.linkpets.core.dao;

import com.linkpets.core.model.CmsActivityAssistance;
import org.apache.ibatis.annotations.Param;

public interface CmsActivityAssistanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_activity_assistance
     *
     * @mbggenerated Tue Mar 26 15:16:53 CST 2019
     */
    int deleteByPrimaryKey(String assistId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_activity_assistance
     *
     * @mbggenerated Tue Mar 26 15:16:53 CST 2019
     */
    int insert(CmsActivityAssistance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_activity_assistance
     *
     * @mbggenerated Tue Mar 26 15:16:53 CST 2019
     */
    int insertSelective(CmsActivityAssistance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_activity_assistance
     *
     * @mbggenerated Tue Mar 26 15:16:53 CST 2019
     */
    CmsActivityAssistance selectByPrimaryKey(String assistId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_activity_assistance
     *
     * @mbggenerated Tue Mar 26 15:16:53 CST 2019
     */
    int updateByPrimaryKeySelective(CmsActivityAssistance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_activity_assistance
     *
     * @mbggenerated Tue Mar 26 15:16:53 CST 2019
     */
    int updateByPrimaryKey(CmsActivityAssistance record);

    /**
     * 根据UserId获取活动助力详情
     *
     * @param activityId
     * @param userId
     * @return
     */
    CmsActivityAssistance getActivityAssistanceByUserId(@Param("activityId") String activityId, @Param("userId") String userId);

    /**
     * 获取助力人数最大值
     *
     * @param activityId
     * @return
     */
    CmsActivityAssistance getMaxOrderOfActivityAssistanceByActivityId(@Param("activityId") String activityId);

    /**
     * 获取助力总数
     *
     * @param activityId
     * @return
     */
    int getActivityAssistNo(String activityId);
}