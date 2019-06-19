//package com.linkpets.security.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
///**
// * @author Xie Chenxi
// * @date 2019-03-29 14:34
// */
//@Configuration
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
//        http.authorizeRequests()
//                .antMatchers("/v2/api-docs", "/configuration/ui",
//                        "/swagger-resources", "/configuration/security",
//                        "/swagger-ui.html", "/course/coursebase/**", "/oauth/**","/**").permitAll()
//                .anyRequest().authenticated();
//    }
//}
