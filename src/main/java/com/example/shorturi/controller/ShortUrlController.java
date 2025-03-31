package com.example.shorturi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlController {
    @PostMapping("/shortUrl")
    public ResponseEntity<String> shortUrl(@RequestBody String originalUrl) {
        return ResponseEntity.ok("http://localhost:8080/abc123");
    }
}
