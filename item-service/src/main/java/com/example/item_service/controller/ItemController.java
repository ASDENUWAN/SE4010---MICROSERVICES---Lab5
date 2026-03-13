package com.example.item_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final List<Map<String, Object>> items = new ArrayList<>(
            List.of(
                    new HashMap<>(Map.of("id", 0, "name", "Book")),
                    new HashMap<>(Map.of("id", 1, "name", "Laptop")),
                    new HashMap<>(Map.of("id", 2, "name", "Phone"))
            )
    );

    @GetMapping
    public List<Map<String, Object>> getItems() {
        return items;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addItem(@RequestBody Map<String, Object> item) {
        int id = items.size();
        item.put("id", id);
        items.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id) {
        if (id < 0 || id >= items.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items.get(id));
    }
}