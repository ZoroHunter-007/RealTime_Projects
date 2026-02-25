package com.example.Agentic_AI.Service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Agentic_AI.DTO.LoginRequest;
import com.example.Agentic_AI.DTO.RegisterRequest;
import com.example.Agentic_AI.Entity.User;
import com.example.Agentic_AI.Repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
private final EmailService emailService;

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayName(request.getDisplayName());

        userRepository.save(user);
        
        emailService.sendMail(user);
    }

    public void login(LoginRequest request) {

        Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );

        // 🔥 THIS CREATES SESSION
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }
    
   
   
    
   
    
}
