package com.example.shorturi.repository;


import com.example.shorturi.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
     List<ShortUrl> findByOriginalUrl(String originalUrl);
}
