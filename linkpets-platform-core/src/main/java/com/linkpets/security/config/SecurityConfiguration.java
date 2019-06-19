//package com.linkpets.security.config;
//
//
//
//import com.linkpets.core.dao.SysUserMapper;
//import com.linkpets.security.service.LpUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//
///**
// * @author Xie Chenxi
// * @date 2019-03-29 11:20
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private SysUserMapper userMapper;
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new LpUserDetailsService(userMapper);
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager manager = super.authenticationManagerBean();
//        return manager;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/userlogin", "/userlogout", "/userjwt", "/v2/api-docs", "/configuration/ui",
//                "/swagger-resources", "/swagger-resources/configuration/security",
//                "/swagger-ui.html", "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index","/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers().anyRequest()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").permitAll();
//    }
//}
