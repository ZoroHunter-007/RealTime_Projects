package com.example.Agentic_AI.Controller;


import com.example.Agentic_AI.CustomUserDetails;
import com.example.Agentic_AI.DTO.UserResponse;
import com.example.Agentic_AI.Entity.User;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/me")
    public UserResponse me(Authentication authentication) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        return new UserResponse(
                user.getEmail(),
                user.getDisplayName()
        );
    }
}
