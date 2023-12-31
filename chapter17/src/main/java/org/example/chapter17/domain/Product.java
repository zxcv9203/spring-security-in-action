package org.example.chapter17.domain;

public class Product {

    private String name;

    private String owner;

    public Product(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}
