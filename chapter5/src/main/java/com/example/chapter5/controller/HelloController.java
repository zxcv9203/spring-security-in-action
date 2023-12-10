package com.example.chapter5.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication user = context.getAuthentication();

        return "hello, " + user.getName() + "!";
    }
}
