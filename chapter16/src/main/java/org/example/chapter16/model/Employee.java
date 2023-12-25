package org.example.chapter16.model;

import java.util.List;

public class Employee {

    private final String name;
    private final List<String> books;

    private final List<String> roles;

    public Employee(String name, List<String> books, List<String> roles) {
        this.name = name;
        this.books = books;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public List<String> getBooks() {
        return books;
    }

    public List<String> getRoles() {
        return roles;
    }
}
