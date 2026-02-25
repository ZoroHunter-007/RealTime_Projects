package com.SpringBoot.ProjectSpringBoot.Response;

import com.SpringBoot.ProjectSpringBoot.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private Long id;
	private String email;
	private String name;
	private Role role;
}
