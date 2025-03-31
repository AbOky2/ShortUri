package com.example.shorturi.service;


import com.example.shorturi.model.ShortUrl;
import com.example.shorturi.repository.ShortUrlRepository;

public class ShortUrlService {
    private ShortUrlRepository repository;
    public ShortUrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public String createShortUrl(String originalUrl) {
        if(originalUrl == null || originalUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("l'URL ne peut pas être vide");
        }
        ShortUrl shortUrl = new ShortUrl("abc123", originalUrl);
        repository.save(shortUrl);
        return "abd123";
    }
}
