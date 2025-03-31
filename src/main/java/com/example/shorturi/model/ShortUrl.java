package com.example.shorturi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ShortUrl {
    @Id
    private String shortCode;
    private String originalUrl;

    public ShortUrl() {}
    public ShortUrl(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }
    public String getShortCode() {
        return shortCode;
    }
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
