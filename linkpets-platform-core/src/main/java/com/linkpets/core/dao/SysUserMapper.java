package com.linkpets.core.dao;

import com.linkpets.core.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {
    @Delete({
            "delete from sys_user",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String userId);

    @Insert({
            "insert into sys_user (user_id, user_account, ",
            "password, user_name, ",
            "user_portrait, mobile_phone, ",
            "email, is_active, ",
            "create_by, create_date, ",
            "is_valid)",
            "values (#{userId,jdbcType=VARCHAR}, #{userAccount,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
            "#{userPortrait,jdbcType=VARCHAR}, #{mobilePhone,jdbcType=VARCHAR}, ",
            "#{email,jdbcType=VARCHAR}, #{isActive,jdbcType=VARCHAR}, ",
            "#{createBy,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
            "#{isValid,jdbcType=INTEGER})"
    })
    int insert(SysUser record);

    int insertSelective(SysUser record);

    @Select({
            "select",
            "user_id, user_account, password, user_name, user_portrait, mobile_phone, email, ",
            "is_active, create_by, create_date, is_valid",
            "from sys_user",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysUserMapper.BaseResultMap")
    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    @Update({
            "update sys_user",
            "set user_account = #{userAccount,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "user_name = #{userName,jdbcType=VARCHAR},",
            "user_portrait = #{userPortrait,jdbcType=VARCHAR},",
            "mobile_phone = #{mobilePhone,jdbcType=VARCHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "is_active = #{isActive,jdbcType=VARCHAR},",
            "create_by = #{createBy,jdbcType=VARCHAR},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "is_valid = #{isValid,jdbcType=INTEGER}",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysUser record);

    @Select({
            "select",
            "user_id, user_account, password, user_name, user_portrait, mobile_phone, ",
            "email, is_active, create_by, create_date, is_valid",
            "from sys_user",
            "where user_account = #{userAccount,jdbcType=VARCHAR}",
            "and is_valid = 1"
    })
    @ResultMap("com.linkpets.core.dao.SysUserMapper.BaseResultMap")
    SysUser getSysUserByUserAccount(String userAccount);

    @Update({
            "update sys_user",
            "set is_valid = 0",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    void delSysUser(String userId);

    void batchDelSysUser(List<String> userIds);

    List<SysUser> getSysUserList(String userAccount, String userName);
}