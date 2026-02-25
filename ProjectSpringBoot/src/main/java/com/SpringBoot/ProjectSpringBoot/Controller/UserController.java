package com.SpringBoot.ProjectSpringBoot.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.ProjectSpringBoot.DTO.LoginDTO;
import com.SpringBoot.ProjectSpringBoot.DTO.RegisterDTO;
import com.SpringBoot.ProjectSpringBoot.Response.AuthResponse;
import com.SpringBoot.ProjectSpringBoot.Service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")

public class UserController {

	private final UserService userService;
	public UserController(UserService service) {
		this.userService=service;
	}
	@PostMapping("/register")
	public String register(@Valid @RequestBody RegisterDTO dto) {
		return userService.register(dto);
	}
	
	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody LoginDTO dto) {
		return userService.login(dto);
	}
	
}
