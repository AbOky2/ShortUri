package com.example.shorturi.service;


import com.example.shorturi.model.ShortUrl;
import com.example.shorturi.repository.ShortUrlRepository;

public class ShortUrlService {
    private ShortUrlRepository repository;
    public ShortUrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public String createShortUrl(String originalUrl) {
        ShortUrl shortUrl = new ShortUrl("abc123", originalUrl);
        repository.save(shortUrl);
        return "abc123";
    }
}
