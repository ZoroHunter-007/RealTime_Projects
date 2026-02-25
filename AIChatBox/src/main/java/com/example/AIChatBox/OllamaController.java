package com.example.AIChatBox;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/chat")
public class OllamaController {

    private final OllamaService service;

    public OllamaController(OllamaService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<String> chat(@RequestBody ChatRequest request) {
        return service.chat(request.getMessage());
    }
}

@Data
class ChatRequest {
    private String message;
}