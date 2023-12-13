package org.example.chapter8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/a")
    public String postEndpointA() {
        return "Works!";
    }

    @GetMapping("/a")
    public String getEndPointA() {
        return "Works!";
    }

    @GetMapping("/a/b")
    public String getEndPointB() {
        return "Works!";
    }

    @GetMapping("/a/b/c")
    public String getEndPointC() {
        return "Works!";
    }
}
