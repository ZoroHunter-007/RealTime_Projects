package com.example.Agentic_AI.Repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Agentic_AI.Entity.ChatSession;
import com.example.Agentic_AI.Entity.User;

public interface ChatSessionRepository extends JpaRepository<ChatSession, UUID> {

    List<ChatSession> findByUserOrderByCreatedAtDesc(User user);
}