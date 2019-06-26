package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CmsAdoptApply {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.apply_id
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String applyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.pet_id
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String petId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.apply_by
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String applyBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.apply_time
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date applyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.sex
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.age
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.kept_pet
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String keptPet;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.marital_status
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String maritalStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.housing_condition
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String housingCondition;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.job
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String job;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.address
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.mobile_phone
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String mobilePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.to_adopter
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String toAdopter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.apply_status
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String applyStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.apply_resp
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String applyResp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.check_time
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date checkTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_adopt_apply.is_valid
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    private String isValid;

    private String formId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.apply_id
     *
     * @return the value of cms_adopt_apply.apply_id
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.apply_id
     *
     * @param applyId the value for cms_adopt_apply.apply_id
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.pet_id
     *
     * @return the value of cms_adopt_apply.pet_id
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getPetId() {
        return petId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.pet_id
     *
     * @param petId the value for cms_adopt_apply.pet_id
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setPetId(String petId) {
        this.petId = petId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.apply_by
     *
     * @return the value of cms_adopt_apply.apply_by
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getApplyBy() {
        return applyBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.apply_by
     *
     * @param applyBy the value for cms_adopt_apply.apply_by
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setApplyBy(String applyBy) {
        this.applyBy = applyBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.apply_time
     *
     * @return the value of cms_adopt_apply.apply_time
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.apply_time
     *
     * @param applyTime the value for cms_adopt_apply.apply_time
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.sex
     *
     * @return the value of cms_adopt_apply.sex
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.sex
     *
     * @param sex the value for cms_adopt_apply.sex
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.age
     *
     * @return the value of cms_adopt_apply.age
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.age
     *
     * @param age the value for cms_adopt_apply.age
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.kept_pet
     *
     * @return the value of cms_adopt_apply.kept_pet
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getKeptPet() {
        return keptPet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.kept_pet
     *
     * @param keptPet the value for cms_adopt_apply.kept_pet
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setKeptPet(String keptPet) {
        this.keptPet = keptPet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.marital_status
     *
     * @return the value of cms_adopt_apply.marital_status
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.marital_status
     *
     * @param maritalStatus the value for cms_adopt_apply.marital_status
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.housing_condition
     *
     * @return the value of cms_adopt_apply.housing_condition
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getHousingCondition() {
        return housingCondition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.housing_condition
     *
     * @param housingCondition the value for cms_adopt_apply.housing_condition
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setHousingCondition(String housingCondition) {
        this.housingCondition = housingCondition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.job
     *
     * @return the value of cms_adopt_apply.job
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getJob() {
        return job;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.job
     *
     * @param job the value for cms_adopt_apply.job
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.address
     *
     * @return the value of cms_adopt_apply.address
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.address
     *
     * @param address the value for cms_adopt_apply.address
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.mobile_phone
     *
     * @return the value of cms_adopt_apply.mobile_phone
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.mobile_phone
     *
     * @param mobilePhone the value for cms_adopt_apply.mobile_phone
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.to_adopter
     *
     * @return the value of cms_adopt_apply.to_adopter
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getToAdopter() {
        return toAdopter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.to_adopter
     *
     * @param toAdopter the value for cms_adopt_apply.to_adopter
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setToAdopter(String toAdopter) {
        this.toAdopter = toAdopter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.apply_status
     *
     * @return the value of cms_adopt_apply.apply_status
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.apply_status
     *
     * @param applyStatus the value for cms_adopt_apply.apply_status
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.apply_resp
     *
     * @return the value of cms_adopt_apply.apply_resp
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getApplyResp() {
        return applyResp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.apply_resp
     *
     * @param applyResp the value for cms_adopt_apply.apply_resp
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setApplyResp(String applyResp) {
        this.applyResp = applyResp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.check_time
     *
     * @return the value of cms_adopt_apply.check_time
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.check_time
     *
     * @param checkTime the value for cms_adopt_apply.check_time
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_adopt_apply.is_valid
     *
     * @return the value of cms_adopt_apply.is_valid
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_adopt_apply.is_valid
     *
     * @param isValid the value for cms_adopt_apply.is_valid
     *
     * @mbggenerated Wed May 22 15:56:36 CST 2019
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}