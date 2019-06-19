package com.linkpets.core.dao;

import com.linkpets.core.model.MessageQueue;
import org.apache.ibatis.annotations.Mapper;

public interface MessageQueueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int deleteByPrimaryKey(String messageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int insert(MessageQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int insertSelective(MessageQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    MessageQueue selectByPrimaryKey(String messageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int updateByPrimaryKeySelective(MessageQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int updateByPrimaryKey(MessageQueue record);
}