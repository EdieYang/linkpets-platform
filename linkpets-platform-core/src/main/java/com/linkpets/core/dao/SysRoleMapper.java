package com.linkpets.core.dao;

import com.linkpets.core.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRoleMapper {
    @Delete({
            "delete from sys_role",
            "where role_id = #{roleId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String roleId);

    @Insert({
            "insert into sys_role (role_id, role_name, ",
            "role_code, role_description, ",
            "create_date, is_valid)",
            "values (#{roleId,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR}, ",
            "#{roleCode,jdbcType=VARCHAR}, #{roleDescription,jdbcType=VARCHAR}, ",
            "#{createDate,jdbcType=TIMESTAMP}, #{isValid,jdbcType=VARCHAR})"
    })
    int insert(SysRole record);

    int insertSelective(SysRole record);

    @Select({
            "select",
            "role_id, role_name, role_code, role_description, create_date, is_valid",
            "from sys_role",
            "where role_id = #{roleId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysRoleMapper.BaseResultMap")
    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    @Update({
            "update sys_role",
            "set role_name = #{roleName,jdbcType=VARCHAR},",
            "role_code = #{roleCode,jdbcType=VARCHAR},",
            "role_description = #{roleDescription,jdbcType=VARCHAR},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "is_valid = #{isValid,jdbcType=VARCHAR}",
            "where role_id = #{roleId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysRole record);


    @Update({
            "update sys_role",
            "set is_valid = '0' ",
            "where role_id = #{roleId,jdbcType=VARCHAR}"
    })
    void delSysRole(String roleId);


    void batchDelSysRole(List<String> roleIdList);


    List<SysRole> getSysRoleList(String roleName, String roleCode);


    List<SysRole> getSysRoleListByUserId(String userId);


    @Select({
            "select",
            "role_id, role_name, role_code, role_description, create_date, is_valid",
            "from sys_role",
            "where role_code = #{roleCode,jdbcType=VARCHAR}",
            "and is_valid = 1"
    })
    @ResultMap("com.linkpets.core.dao.SysRoleMapper.BaseResultMap")
    SysRole getSysRoleByRoleCode(String roleCode);

    @Select({
            "select",
            "role_id",
            "from sys_role",
            "where role_code = #{roleCode,jdbcType=VARCHAR}",
            "and is_valid = 1"
    })
    String getRoleIdByRoleCode(String roleCode);

}