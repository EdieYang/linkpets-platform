package com.linkpets.core.dao;

import com.linkpets.core.model.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysPermissionMapper {
    @Delete({
            "delete from sys_permission",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into sys_permission (id, permission_name, ",
            "permission_code, permission_description, ",
            "del_flag, create_date)",
            "values (#{id,jdbcType=VARCHAR}, #{permissionName,jdbcType=VARCHAR}, ",
            "#{permissionCode,jdbcType=VARCHAR}, #{permissionDescription,jdbcType=VARCHAR}, ",
            "#{delFlag,jdbcType=INTEGER}, #{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    @Select({
            "select",
            "id, permission_name, permission_code, permission_description, del_flag, create_date",
            "from sys_permission",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysPermissionMapper.BaseResultMap")
    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    @Update({
            "update sys_permission",
            "set permission_name = #{permissionName,jdbcType=VARCHAR},",
            "permission_code = #{permissionCode,jdbcType=VARCHAR},",
            "permission_description = #{permissionDescription,jdbcType=VARCHAR},",
            "del_flag = #{delFlag,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> getSysPermissionList(String permissionName, String permissionCode);

    @Update({
            "update sys_permission",
            "set del_flag = '0'",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    void delSysPermission(String id);

    void batchDelSysPermission(List<String> idList);

    List<SysPermission> getSysPermissionListByUserId(String userId);
}