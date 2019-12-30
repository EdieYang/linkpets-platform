package com.linkpets.core.dao;

import com.linkpets.core.model.SysRoleMenuRel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRoleMenuRelMapper {
    @Delete({
            "delete from sys_role_menu_rel",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into sys_role_menu_rel (id, role_id, ",
            "menu_id, create_date, ",
            "del_flag)",
            "values (#{id,jdbcType=VARCHAR}, #{roleId,jdbcType=VARCHAR}, ",
            "#{menuId,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
            "#{delFlag,jdbcType=VARCHAR})"
    })
    int insert(SysRoleMenuRel record);

    int insertSelective(SysRoleMenuRel record);

    @Select({
            "select",
            "id, role_id, menu_id, create_date, del_flag",
            "from sys_role_menu_rel",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysRoleMenuRelMapper.BaseResultMap")
    SysRoleMenuRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRoleMenuRel record);

    @Update({
            "update sys_role_menu_rel",
            "set role_id = #{roleId,jdbcType=VARCHAR},",
            "menu_id = #{menuId,jdbcType=VARCHAR},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "del_flag = #{delFlag,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysRoleMenuRel record);

    @Update({
            "update sys_role_menu_rel",
            "set del_flag = 0",
            "where role_id = #{roleId,jdbcType=VARCHAR}"
    })
    void delAllMenusByRoleId(String roleId);

    void insertBatch(List<SysRoleMenuRel> sysRoleMenuRelList);
}