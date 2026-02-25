package com.example.Agentic_AI.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Agentic_AI.Entity.ChatRequest;
import com.example.Agentic_AI.GeminiClient.ChatAgent;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class GeminiController {

	private final ChatAgent chatAgent;
	
	@PostMapping("/api/ai")
	public Mono<String>ai(@RequestBody ChatRequest chatRequest){
		return chatAgent.chat(chatRequest.getMessage().trim());
	}
}
