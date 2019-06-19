//package com.linkpets.security.service;
//
//import com.linkpets.core.dao.SysUserMapper;
//import com.linkpets.core.model.SysUser;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * @author Xie Chenxi
// * @date 2019-03-29 12:25
// */
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class LpUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private SysUserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        SysUser sysUser = userMapper.selectByUserName(userName);
//        if (sysUser == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        return LpUserFactory.get(sysUser);
//    }
//}
