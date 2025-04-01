package com.example.shorturi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(content().string(matchesRegex("http://localhost:8080/[a-zA-Z0-9]{6}")));
    }

    @Test
    public void shouldReturnBadRequestForEmptyUrl() throws Exception {
        String originalUrl = "";
        mockMvc.perform(post("/shortUrl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"originalUrl\": \"" + originalUrl + "\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRedirectToOriginalUrl() throws Exception {
        String originalUrl = "https://www.google.com";
        String response = mockMvc.perform(post("/shortUrl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"originalUrl\": \"" + originalUrl + "\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String shortCode = response.replace("http://localhost:8080/", "");

        mockMvc.perform(get("/" + shortCode))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", originalUrl));
    }

    @Test
    public void shouldListShortUrlsForOriginalUrl() throws Exception {
        String originalUrl = "https://www.google.com";
        //on crée 2 Urls courtes pour l'url originale
        mockMvc.perform(post("/shortUrl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"originalUrl\": \"" + originalUrl + "\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/shortUrl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"originalUrl\": \"" + originalUrl + "\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/shortUrls")
                .param("originalUrl", originalUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("abc123"))
                .andExpect(jsonPath("$[1]").value("def456"));

    }

}
