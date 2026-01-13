package com.example.AIChatBox;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api/ollama")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OllamaController {

    private final OllamaService ollamaService;

    @PostMapping(value = "/chat-stream", produces = "text/event-stream")
    public Flux<String> chatStream(@RequestBody ChatMessageRequest request) {
        return ollamaService.chatStream(request.getMessage());
    }
}
