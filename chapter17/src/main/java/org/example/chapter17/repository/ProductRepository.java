package org.example.chapter17.repository;

import org.example.chapter17.domain.Product;
import org.example.chapter17.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @PostFilter("filterObject.owner == authentication.name")
    List<Product> findProductByNameContains(String text);
}
