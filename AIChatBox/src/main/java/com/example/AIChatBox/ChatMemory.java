package com.example.AIChatBox;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ChatMemory {

    private final List<OllamaRequest.Message> messages = new ArrayList<>();

    public ChatMemory() {
        // system message once
        messages.add(new OllamaRequest.Message(
                "system",
                "You are a helpful Java and Spring Boot assistant"
        ));
    }

    public List<OllamaRequest.Message> getMessages() {
        return messages;
    }

    public void addUserMessage(String message) {
        messages.add(new OllamaRequest.Message("user", message));
    }

    public void addAssistantMessage(String message) {
        messages.add(new OllamaRequest.Message("assistant", message));
    }
}
