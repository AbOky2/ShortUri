package com.example.shorturi.controller;

import com.example.shorturi.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


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
        String originalUrl = service.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }

    @GetMapping("/shortUrls")
    public ResponseEntity<List<String>> listShortUrls(@RequestParam String originalUrl){
        List<String> result = Arrays.asList("abc123","def456");
        System.out.println("Returning: " + result);
        return ResponseEntity.ok(result);
    }
}
