package com.example.Agentic_AI.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.Agentic_AI.CustomUserDetails;
import com.example.Agentic_AI.Entity.ChatMessage;
import com.example.Agentic_AI.Entity.ChatSession;
import com.example.Agentic_AI.Repo.ChatMessageRepository;
import com.example.Agentic_AI.Service.ChatSessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;
    private final ChatMessageRepository messageRepository;

    // 🔹 Sidebar: load chat history
    @GetMapping
    public List<ChatSession> getChats(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return chatSessionService.getUserChats(userDetails.getUser());
    }

    // 🔹 New Chat button
    @PostMapping
    public ChatSession newChat(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return chatSessionService.createNew(userDetails.getUser());
    }

    // 🔹 Load messages of selected chat
    @GetMapping("/{chatId}/messages")
    public List<ChatMessage> getMessages(@PathVariable UUID chatId) {
        return messageRepository.findByChatSessionOrderByCreatedAt(
                chatSessionService.getById(chatId)
        );
    }
}
