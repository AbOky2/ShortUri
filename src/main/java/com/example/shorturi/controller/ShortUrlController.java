package com.example.shorturi.controller;

import com.example.shorturi.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShortUrlController {
    @Autowired
    private ShortUrlService service;
    @PostMapping("/shortUrl")
    public ResponseEntity<String> shortUrl(@RequestBody UrlRequest request) {
        String shortCode = service.createShortUrl(request.getOriginalUrl());
        return ResponseEntity.ok("http://localhost:8080/"+shortCode);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "https://www.google.com")
                .build();
    }
}
