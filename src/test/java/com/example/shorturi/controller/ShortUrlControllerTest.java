package com.example.shorturi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShortUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateShortUrl() throws Exception {
        String originalUrl = "https://www.google.com";
        mockMvc.perform(post("/shortUrl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"originalUrl\": \"" + originalUrl + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("http://localhost:8080/abc123"));
    }

}
