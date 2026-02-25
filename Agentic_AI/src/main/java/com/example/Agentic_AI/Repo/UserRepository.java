package com.example.Agentic_AI.Repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Agentic_AI.Entity.User;



public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findByEmail(String email);
}
