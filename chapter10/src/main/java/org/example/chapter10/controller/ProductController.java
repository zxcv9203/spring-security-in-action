package org.example.chapter10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        logger.info("Adding product : {}", name);
        return "main.html";
    }
}
