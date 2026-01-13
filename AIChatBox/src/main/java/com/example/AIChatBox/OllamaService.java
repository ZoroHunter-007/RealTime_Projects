package com.example.AIChatBox;





import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;



@Service
@RequiredArgsConstructor
public class OllamaService {

    private final WebClient webClient;
    private final OllamaConfig config;

    public Flux<String> chatStream(String userMessage) {

        // 🔥 NO MEMORY — clean prompt every time
        List<OllamaRequest.Message> messages = List.of(
        		new OllamaRequest.Message(
        				  "system",
        				  "You are a helpful assistant. Respond with proper spacing and readable sentences."
        				),
            new OllamaRequest.Message("user", userMessage)
        );

        OllamaRequest request = new OllamaRequest(
                config.getModel(),
                messages,
                true
        );

        return webClient.post()
                .uri(config.getBaseUrl() + "/api/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(OllamaResponse.class)
                .map(res -> res.getMessage().getContent());
    }
}
