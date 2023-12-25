package org.example.chapter16.service;

import org.example.chapter16.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<String, Employee> records = Map.of(
            "kim", new Employee("kim", List.of("object"), List.of("reader", "writer")),
            "park", new Employee("park", List.of("spring security in action"), List.of("reader"))
    );

    @PostAuthorize("returnObject.roles.contains('writer')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
