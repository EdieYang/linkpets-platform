package com.linkpets.core.model;

import java.util.Date;
import java.util.List;

public class SdCatalog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sd_catalog.id
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sd_catalog.catalog_name
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    private String catalogName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sd_catalog.sort
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sd_catalog.create_date
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sd_catalog.sid
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    private Integer sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sd_catalog.type
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    private Integer type;

    List<SdCatalogList> catalogLists;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sd_catalog.id
     *
     * @return the value of sd_catalog.id
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sd_catalog.id
     *
     * @param id the value for sd_catalog.id
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sd_catalog.catalog_name
     *
     * @return the value of sd_catalog.catalog_name
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public String getCatalogName() {
        return catalogName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sd_catalog.catalog_name
     *
     * @param catalogName the value for sd_catalog.catalog_name
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sd_catalog.sort
     *
     * @return the value of sd_catalog.sort
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sd_catalog.sort
     *
     * @param sort the value for sd_catalog.sort
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sd_catalog.create_date
     *
     * @return the value of sd_catalog.create_date
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sd_catalog.create_date
     *
     * @param createDate the value for sd_catalog.create_date
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sd_catalog.sid
     *
     * @return the value of sd_catalog.sid
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sd_catalog.sid
     *
     * @param sid the value for sd_catalog.sid
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sd_catalog.type
     *
     * @return the value of sd_catalog.type
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sd_catalog.type
     *
     * @param type the value for sd_catalog.type
     *
     * @mbggenerated Wed Aug 21 23:23:28 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }


    public List<SdCatalogList> getCatalogLists() {
        return catalogLists;
    }

    public void setCatalogLists(List<SdCatalogList> catalogLists) {
        this.catalogLists = catalogLists;
    }
}