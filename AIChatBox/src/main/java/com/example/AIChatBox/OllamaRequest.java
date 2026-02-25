package com.example.AIChatBox;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OllamaRequest {

    private String model;
    private List<Message> messages;
    private boolean stream = false;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}