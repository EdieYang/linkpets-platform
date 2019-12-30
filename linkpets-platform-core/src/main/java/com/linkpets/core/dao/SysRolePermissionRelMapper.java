package com.linkpets.core.dao;

import com.linkpets.core.model.SysRolePermissionRel;
import com.linkpets.core.respEntity.system.SysRolePermissionRes;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRolePermissionRelMapper {
    @Delete({
            "delete from sys_role_permission_rel",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into sys_role_permission_rel (id, role_id, ",
            "permission_id, del_flag, ",
            "create_date)",
            "values (#{id,jdbcType=VARCHAR}, #{roleId,jdbcType=VARCHAR}, ",
            "#{permissionId,jdbcType=VARCHAR}, #{delFlag,jdbcType=INTEGER}, ",
            "#{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(SysRolePermissionRel record);

    int insertSelective(SysRolePermissionRel record);

    @Select({
            "select",
            "id, role_id, permission_id, del_flag, create_date",
            "from sys_role_permission_rel",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysRolePermissionRelMapper.BaseResultMap")
    SysRolePermissionRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRolePermissionRel record);

    @Update({
            "update sys_role_permission_rel",
            "set role_id = #{roleId,jdbcType=VARCHAR},",
            "permission_id = #{permissionId,jdbcType=VARCHAR},",
            "del_flag = #{delFlag,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysRolePermissionRel record);

    @Select({
            "select",
            "id, role_id, permission_id, del_flag, create_date",
            "from sys_role_permission_rel",
            "where permission_id = #{permissionId,jdbcType=VARCHAR}",
            "and role_id = #{roleId,jdbcType=VARCHAR}",
            "and del_flag = 1 "
    })
    @ResultMap("com.linkpets.core.dao.SysRolePermissionRelMapper.BaseResultMap")
    SysRolePermissionRel selectByPermissionIdAndRoleId(String permissionId, String roleId);

    List<SysRolePermissionRes> getSysRolePermissionList(String roleId, String permissionName, String permissionCode);
}