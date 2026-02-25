package com.AgenticAi.AIProject.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.AgenticAi.AIProject.Custom.CustomUserDetails;
import com.AgenticAi.AIProject.Entity.ChatMessage;
import com.AgenticAi.AIProject.Entity.ChatRequest;
import com.AgenticAi.AIProject.Entity.ChatSession;
import com.AgenticAi.AIProject.Entity.User;
import com.AgenticAi.AIProject.Gemini.ChatAgent;
import com.AgenticAi.AIProject.Repository.ChatMessageRepository;
import com.AgenticAi.AIProject.Service.ChatSessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GeminiController {

    private final ChatAgent chatAgent;
    private final ChatSessionService chatSessionService;
    private final ChatMessageRepository chatMessageRepository;

    // ✅ MAIN CHAT ENDPOINT
    @PostMapping(
            value = "/api/ai",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<String, Object> ai(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ChatRequest chatRequest) {

        if (userDetails == null) {
            return Map.of("reply", "Please login again.");
        }

        
        User user = userDetails.getUser();

        UUID sessionId = chatRequest.getSessionId();

        ChatSession session = chatSessionService.getById(sessionId);

        // Security check
        if (!session.getUser().getId()
                .equals(user.getId())) {
            throw new RuntimeException("Unauthorized session access");
        }

        String reply = chatAgent.chatBlocking(
                session,
                chatRequest.getMessage().trim()
        );

        return Map.of("reply", reply);
    }

    // ✅ GET LATEST SESSION (for page load)
    @GetMapping("/api/session/latest")
    public ChatSession getLatestSession(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            throw new RuntimeException("User not authenticated");
        }

        return chatSessionService.getLatest(userDetails.getUser());
    }

    // ✅ GET CHAT HISTORY
    @GetMapping("/api/chat/{sessionId}")
    public List<ChatMessage> getChatHistory(
            @PathVariable UUID sessionId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        ChatSession session = chatSessionService.getById(sessionId);

        // Optional security check (recommended)
        if (!session.getUser().getId()
                .equals(userDetails.getUser().getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        return chatMessageRepository
                .findByChatSessionOrderByCreatedAt(session);
    }

    // Debug
    @GetMapping("/api/debug-auth")
    public String debugAuth(Authentication authentication) {
        if (authentication == null) {
            return "NOT AUTHENTICATED";
        }
        return "AUTHENTICATED: " + authentication.getName();
    }
}