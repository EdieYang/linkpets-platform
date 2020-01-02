package com.linkpets.core.dao;

import com.linkpets.core.model.SysUserRoleRel;
import com.linkpets.core.respEntity.system.SysRoleUserRes;
import com.linkpets.core.respEntity.system.SysUserRoleRes;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserRoleRelMapper {
    @Delete({
            "delete from sys_user_role_rel",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into sys_user_role_rel (id, user_id, ",
            "role_id, is_valid, ",
            "create_date)",
            "values (#{id,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
            "#{roleId,jdbcType=VARCHAR}, #{isValid,jdbcType=INTEGER}, ",
            "#{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(SysUserRoleRel record);

    int insertSelective(SysUserRoleRel record);

    @Select({
            "select",
            "id, user_id, role_id, is_valid, create_date",
            "from sys_user_role_rel",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysUserRoleRelMapper.BaseResultMap")
    SysUserRoleRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRoleRel record);

    @Update({
            "update sys_user_role_rel",
            "set user_id = #{userId,jdbcType=VARCHAR},",
            "role_id = #{roleId,jdbcType=VARCHAR},",
            "is_valid = #{isValid,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysUserRoleRel record);

    List<SysRoleUserRes> getSysRoleUserPage(String roleId, String userAccount, String userName);

    @Select({
            "select",
            "id, user_id, role_id, is_valid, create_date",
            "from sys_user_role_rel",
            "where user_id = #{userId,jdbcType=VARCHAR}",
            "and role_id = #{roleId,jdbcType=VARCHAR}",
            "and is_valid = 1",
    })
    @ResultMap("com.linkpets.core.dao.SysUserRoleRelMapper.BaseResultMap")
    SysUserRoleRel selectByUserIdAndRoleId(String userId, String roleId);

    List<SysUserRoleRes> getSysUserRolePage(String userId, String roleName, String roleCode);


    @Delete({
            "delete from sys_user_role_rel",
            "where user_id = #{userId,jdbcType=VARCHAR}"
    })
    void deleteByUserId(String userId);

}