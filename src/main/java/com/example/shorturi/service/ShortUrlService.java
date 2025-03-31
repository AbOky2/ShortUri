package com.example.shorturi.service;


import com.example.shorturi.model.ShortUrl;
import com.example.shorturi.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShortUrlService {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 6;
    private ShortUrlRepository repository;
    public ShortUrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public String createShortUrl(String originalUrl) {
        if(originalUrl == null || originalUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("l'URL ne peut pas être vide");
        }
        String shortCode = generateShortCode();
        while(repository.existsById(shortCode)){
            shortCode = generateShortCode();
        }
        ShortUrl shortUrl = new ShortUrl(shortCode, originalUrl);
        repository.save(shortUrl);
        return shortCode;
    }

    private String generateShortCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <LENGTH; i++){
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    public String getOriginalUrl(String shortCode) {
        return repository.findById(shortCode)
                .map(ShortUrl::getOriginalUrl)
                .orElseThrow(() ->new RuntimeException("Url courte non trouvable"));
    }
}
