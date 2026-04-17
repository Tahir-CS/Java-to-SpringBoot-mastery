package com.tahir.springbootfirstusage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        // Returning a Map in Spring Boot becomes JSON automatically.
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome! Your first Spring Boot API is running.");
        response.put("tip", "Try /api/students endpoint next.");
        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "springboot-first-usage");
        return response;
    }
}
