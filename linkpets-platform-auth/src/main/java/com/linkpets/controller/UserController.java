package com.linkpets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Xie Chenxi
 * @date 2019-03-29 11:20
 */
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("me")
    public Principal me(Principal principal) {
        return principal;
    }
}