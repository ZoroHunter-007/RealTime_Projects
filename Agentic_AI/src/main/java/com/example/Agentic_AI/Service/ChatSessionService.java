package com.example.Agentic_AI.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.Agentic_AI.Entity.ChatSession;
import com.example.Agentic_AI.Entity.User;
import com.example.Agentic_AI.Repo.ChatSessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository sessionRepository;

    public ChatSession createNew(User user) {
        ChatSession session = new ChatSession();
        session.setUser(user);
        session.setTitle("New Chat");
        session.setCreatedAt(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    public List<ChatSession> getUserChats(User user) {
        return sessionRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public ChatSession getById(UUID id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
    }
}
