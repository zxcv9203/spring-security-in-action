package org.example.chapter17.controller;

import org.example.chapter17.domain.Product;
import org.example.chapter17.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/sell")
    public List<Product> sellProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("beer", "kim"));
        products.add(new Product("candy", "kim"));
        products.add(new Product("chocolate", "park"));

        return productService.sellProducts(products);
    }
}
