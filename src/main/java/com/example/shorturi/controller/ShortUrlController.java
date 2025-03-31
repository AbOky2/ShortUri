package com.example.shorturi.controller;

import com.example.shorturi.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlController {
    @Autowired
    private ShortUrlService service;
    @PostMapping("/shortUrl")
    public ResponseEntity<String> shortUrl(@RequestBody String originalUrl) {
        String shortCode = service.createShortUrl(originalUrl);
        return ResponseEntity.ok("http://localhost:8080/"+shortCode);
    }
}
