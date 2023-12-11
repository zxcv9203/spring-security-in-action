package com.example.chapter6.domain.product.repository;

import com.example.chapter6.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
