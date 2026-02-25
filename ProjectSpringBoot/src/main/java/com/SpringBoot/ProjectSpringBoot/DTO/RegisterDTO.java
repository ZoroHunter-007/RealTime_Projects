package com.SpringBoot.ProjectSpringBoot.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {

	@NotBlank(message = "Username is required")
	private String name;
	
	@Email(message ="Inavalid Email Format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@Size(min = 6, message = "Password at least 6 characters")
	private String password;
	
	private String adminSecret;
	
}
