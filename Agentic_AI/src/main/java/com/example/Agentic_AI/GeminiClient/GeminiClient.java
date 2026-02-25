package com.example.Agentic_AI.GeminiClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class GeminiClient {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apikey;

    @Value("${gemini.model.primary}")
    private String primaryModel;

    @Value("${gemini.model.fallback}")
    private String fallBackModel;

    public GeminiClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<String> generate(String prompt) {

        return call(primaryModel, prompt)
            .onErrorResume(
                WebClientResponseException.TooManyRequests.class,
                ex -> call(fallBackModel, prompt)
            )
            .onErrorResume(ex ->
                Mono.just("⏸ I’m under heavy load right now 😅\nPlease try again shortly.")
            );
    }

    private Mono<String> call(String model, String prompt) {

        String url =
            "https://generativelanguage.googleapis.com/v1beta/models/"
            + model
            + ":generateContent?key=" + apikey;

        // ✅ SAFE prompt escaping
        String safePrompt = prompt
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n");

        String body = """
        {
          "contents": [
            {
              "parts": [
                { "text": "%s" }
              ]
            }
          ]
        }
        """.formatted(safePrompt);

        return webClient.post()
            .uri(url)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(this::extractText);
    }

    // ✅ CORRECT + DEFENSIVE PARSER
    private String extractText(JsonNode response) {
        try {
            JsonNode candidates = response.get("candidates");
            if (candidates == null || !candidates.isArray() || candidates.isEmpty()) {
                return "⚠️ No response from AI.";
            }

            JsonNode content = candidates.get(0).get("content");
            if (content == null) {
                return "⚠️ Empty AI response.";
            }

            JsonNode parts = content.get("parts");
            if (parts == null || !parts.isArray() || parts.isEmpty()) {
                return "⚠️ AI returned no text.";
            }

            JsonNode textNode = parts.get(0).get("text");
            if (textNode == null) {
                return "⚠️ AI response missing text.";
            }

            return textNode.asText();

        } catch (Exception e) {
            return "⚠️ I couldn’t understand the response properly.";
        }
    }
}
