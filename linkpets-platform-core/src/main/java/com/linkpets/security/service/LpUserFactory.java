//package com.linkpets.security.service;
//
//import com.linkpets.core.model.SysUser;
//import com.linkpets.security.model.LpUser;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author Xie Chenxi
// * @date 2019-03-29 12:44
// */
//public class LpUserFactory {
//    private LpUserFactory() {
//
//    }
//
//    public static LpUser get(SysUser sysUser) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String finalPassword = "{bcrypt}" + bCryptPasswordEncoder.encode(sysUser.getPassword());
//        sysUser.setPassword(finalPassword);
//        return new LpUser(sysUser);
//    }
//}
