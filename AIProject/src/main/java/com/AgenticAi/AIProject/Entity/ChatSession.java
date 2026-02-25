package com.AgenticAi.AIProject.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chat_session")
@Data
public class ChatSession {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt;
}
