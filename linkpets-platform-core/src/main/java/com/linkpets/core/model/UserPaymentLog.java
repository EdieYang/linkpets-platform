package com.linkpets.core.model;

import java.util.Date;

public class UserPaymentLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.log_id
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private Integer logId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.user_id
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.pay_way
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private String payWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.amount
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.pay_datetime
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private Date payDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.pay_info
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private String payInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_payment_log.del_flag
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    private String delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.log_id
     *
     * @return the value of user_payment_log.log_id
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.log_id
     *
     * @param logId the value for user_payment_log.log_id
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.user_id
     *
     * @return the value of user_payment_log.user_id
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.user_id
     *
     * @param userId the value for user_payment_log.user_id
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.pay_way
     *
     * @return the value of user_payment_log.pay_way
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.pay_way
     *
     * @param payWay the value for user_payment_log.pay_way
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.amount
     *
     * @return the value of user_payment_log.amount
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.amount
     *
     * @param amount the value for user_payment_log.amount
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.pay_datetime
     *
     * @return the value of user_payment_log.pay_datetime
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public Date getPayDatetime() {
        return payDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.pay_datetime
     *
     * @param payDatetime the value for user_payment_log.pay_datetime
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.pay_info
     *
     * @return the value of user_payment_log.pay_info
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public String getPayInfo() {
        return payInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.pay_info
     *
     * @param payInfo the value for user_payment_log.pay_info
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_payment_log.del_flag
     *
     * @return the value of user_payment_log.del_flag
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_payment_log.del_flag
     *
     * @param delFlag the value for user_payment_log.del_flag
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}