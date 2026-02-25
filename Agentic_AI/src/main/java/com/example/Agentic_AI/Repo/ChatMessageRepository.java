package com.example.Agentic_AI.Repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Agentic_AI.Entity.ChatMessage;
import com.example.Agentic_AI.Entity.ChatSession;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {

    List<ChatMessage> findByChatSessionOrderByCreatedAt(ChatSession session);
}