package com.SpringBoot.ProjectSpringBoot.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.ProjectSpringBoot.Response.UserResponse;
import com.SpringBoot.ProjectSpringBoot.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final UserService userService;
	
	@GetMapping("/allUsers")
	public List<UserResponse>getAllUsers(){
		return userService.getAllUsers();
	}
}
