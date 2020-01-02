package com.linkpets.core.dao;

import com.linkpets.core.model.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysMenuMapper {
    @Delete({
            "delete from sys_menu",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into sys_menu (id, parent_id, ",
            "title, path, icon, ",
            "sort, create_date, ",
            "is_valid)",
            "values (#{id,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
            "#{title,jdbcType=VARCHAR}, #{path,jdbcType=VARCHAR}, #{icon,jdbcType=VARCHAR}, ",
            "#{sort,jdbcType=INTEGER}, #{createDate,jdbcType=TIMESTAMP}, ",
            "#{isValid,jdbcType=VARCHAR})"
    })
    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    @Select({
            "select",
            "id, parent_id, title, path, icon, sort, create_date, is_valid",
            "from sys_menu",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("com.linkpets.core.dao.SysMenuMapper.BaseResultMap")
    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    @Update({
            "update sys_menu",
            "set parent_id = #{parentId,jdbcType=VARCHAR},",
            "title = #{title,jdbcType=VARCHAR},",
            "path = #{path,jdbcType=VARCHAR},",
            "icon = #{icon,jdbcType=VARCHAR},",
            "sort = #{sort,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "is_valid = #{isValid,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysMenu record);

    @Update({
            "update sys_menu",
            "set is_valid = '0'",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    void delSysMenu(String menuId);


    @Select({
            "select",
            "id, parent_id, title, path, icon, sort, create_date, is_valid",
            "from sys_menu",
            "where is_valid = '1'",
            "order by sort"
    })
    @ResultMap("com.linkpets.core.dao.SysMenuMapper.BaseResultMap")
    SysMenu getSysMenuList();


    List<SysMenu> getSysMenuListByParentId(String parentId);

    List<SysMenu> getSysMenuListByParentIds(List<String> parentIds);

    List<SysMenu> getSysRoleMenuList(String roleId);

    List<SysMenu> getSysMenuListByUserId(String userId);
}