//package com.linkpets.security.model;
//
//import com.linkpets.core.model.SysUser;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//
///**
// * @author Xie Chenxi
// * @date 2019-03-29 12:35
// */
//public class LpUser extends User {
//
//    public LpUser(SysUser sysUser) {
//        super(sysUser.getUserName(), sysUser.getPassword(), sysUser.getIsActive() == 1, true, true, sysUser.getIsActive() == 1,
//                AuthorityUtils.createAuthorityList(sysUser.getRoleId()));
//    }
//}
