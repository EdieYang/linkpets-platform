package com.linkpets.core.dao;

import com.linkpets.core.model.PetSupply;

public interface PetSupplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_supply
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int deleteByPrimaryKey(String supplyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_supply
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int insert(PetSupply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_supply
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int insertSelective(PetSupply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_supply
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    PetSupply selectByPrimaryKey(String supplyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_supply
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int updateByPrimaryKeySelective(PetSupply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_supply
     *
     * @mbggenerated Fri Mar 15 18:40:44 CST 2019
     */
    int updateByPrimaryKey(PetSupply record);
}