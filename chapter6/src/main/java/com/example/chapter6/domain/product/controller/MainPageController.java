package com.example.chapter6.domain.product.controller;

import com.example.chapter6.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final ProductService productService;

    @GetMapping("/main")
    public String goMain(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        model.addAttribute("products", productService.findAll());

        return "main.html";
    }
}
