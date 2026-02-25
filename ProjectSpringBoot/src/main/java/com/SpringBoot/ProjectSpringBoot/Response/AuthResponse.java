package com.SpringBoot.ProjectSpringBoot.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

	private String token;
	private UserResponse response;
	
}
