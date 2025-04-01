package com.example.shorturi.service;

import com.example.shorturi.model.ShortUrl;
import com.example.shorturi.repository.ShortUrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceTest {
    @Mock
    private ShortUrlRepository repository;

    @InjectMocks
    private ShortUrlService service;

    @Test
    public void shouldCreateShortUrl() {
        String originalUrl = "https://google.com";
        String shortCode = service.createShortUrl(originalUrl);
        assertEquals("abc123", shortCode);
        verify(repository).save(any(ShortUrl.class));
    }

    @Test
    public void ShouldThrowExceptionForEmptyUrl(){
        String originalUrl = "";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()
                -> service.createShortUrl(originalUrl));
        assertEquals("l'URL ne peut pas être vide", exception.getMessage());
    }

    @Test
    public void shouldGenerateUniqueShortCode(){
        String originalUrl = "https://google.com";
        String shortCode = service.createShortUrl(originalUrl);
        assertNotEquals("abc123", shortCode);
    }

    @Test
    public void shouldReturnOriginalShortCode(){
        String originalUrl = "https://google.com";
        String shortCode = "abc123";
        when(repository.findById(shortCode)).thenReturn(Optional.of(new ShortUrl(originalUrl, shortCode)));
        String result = service.getOriginalUrl(shortCode);
        assertEquals("abc123", result);
    }

    @Test
    public void shouldListShortCodesForOriginalUrl(){
        String originalUrl = "https://google.com";
        List<ShortUrl> shortUrls = Arrays.asList(
                new ShortUrl("abc123", originalUrl),
                new ShortUrl("def456", originalUrl)
        );

        when(repository.findByOriginalUrl(originalUrl)).thenReturn(shortUrls);
        List<String> shortCodes = service.listShortCodes(originalUrl);
        assertEquals(2, shortCodes.size());
        assertTrue(shortCodes.contains("abc123"));
        assertTrue(shortCodes.contains("def456"));
    }

    @Test
    public void shouldIncrementVisitCountOnGetOriginalUrl(){
        String originalUrl = "https://google.com";
        String shortCode = "abc123";

        ShortUrl shortUrl = new ShortUrl(originalUrl, shortCode);

        when(repository.findById(shortCode)).thenReturn(Optional.of(shortUrl));
        when(repository.save(any(ShortUrl.class))).thenReturn(shortUrl);

        String result = service.getOriginalUrl(shortCode);
        assertEquals(originalUrl, result);
        assertEquals(1, service.getVisitCount());

        verify(repository).save(shortUrl);
    }

}
