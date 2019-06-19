package com.linkpets.cms.manage.dao;

import com.linkpets.cms.manage.model.SysUserCustom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/15
 */
public interface SysUserCustomMapper {


    /**
     * 获取系统账号列表
     * @param orgId
     * @param chainId
     * @return
     */
    List<SysUserCustom> listSysUserCustom(@Param("orgId") String orgId, @Param("chainId") String chainId);

    /**
     * 根据用户名获取账号信息
     * @param userName
     * @return
     */
    SysUserCustom getSysUserByUserName(@Param("userName") String userName);

}
