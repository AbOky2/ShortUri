package com.example.shorturi.service;

import com.example.shorturi.model.ShortUrl;
import com.example.shorturi.repository.ShortUrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
}
