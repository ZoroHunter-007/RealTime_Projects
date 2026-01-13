package com.example.AIChatBox;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ollama")
public class OllamaConfig {

    /**
     * Maps to: ollama.base-url in application.properties
     */
    private String baseUrl;

    /**
     * Maps to: ollama.model in application.properties
     */
    private String model;
}

