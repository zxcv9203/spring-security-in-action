package com.example.chapter5.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello(Authentication user) {
        return "hello, " + user.getName() + "!";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        System.out.println("good bye, " + username);
    }

    @GetMapping("/hi")
    public String hi() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        try {
            DelegatingSecurityContextCallable<String> contextTask = new DelegatingSecurityContextCallable<>(task);
            return "hi, " + executorService.submit(contextTask).get() + "!";
        } finally {
            executorService.shutdown();
        }
    }

    @GetMapping("/home")
    public String goHome() throws Exception{
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService = new DelegatingSecurityContextExecutorService(executorService);
        try {
            return "welcome home " + executorService.submit(task).get() + "!";
        } finally {
            executorService.shutdown();
        }
    }
}
