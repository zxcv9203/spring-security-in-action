package com.example.chapter6.domain.product.model;

import com.example.chapter6.domain.product.model.type.Currency;
import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
