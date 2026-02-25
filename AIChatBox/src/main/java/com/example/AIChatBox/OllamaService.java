package com.example.AIChatBox;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OllamaService {

    private final WebClient webClient =
            WebClient.create("http://localhost:11434");

    public Mono<String> chat(String userMessage) {

        // 1️⃣ SYSTEM MESSAGE (RULES FOR AI)
        OllamaRequest.Message system =
            new OllamaRequest.Message(
                "system",
                "Act like a close, friendly buddy chatting on WhatsApp. " +
                		"Be relaxed, warm, and natural. " +
                		"Use casual language like a real friend. " +
                		"Reply in short numbered steps (max 3). " +
                		"Each step should feel human and conversational. " +
                		"Light emojis are allowed 🙂. " +
                		"No formal tone. No paragraphs."

            );

        // 2️⃣ USER MESSAGE
        OllamaRequest.Message user =
            new OllamaRequest.Message("user", userMessage);

        // 3️⃣ BUILD REQUEST
        OllamaRequest request =
            new OllamaRequest(
                "llama3",
                List.of(system, user),
                false
            );

        // 4️⃣ CALL OLLAMA
        return webClient.post()
                .uri("/api/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OllamaResponse.class)
                .map(res -> res.getMessage().getContent());
    }
}