package com.SpringBoot.ProjectSpringBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringBoot.ProjectSpringBoot.DTO.LoginDTO;
import com.SpringBoot.ProjectSpringBoot.DTO.RegisterDTO;
import com.SpringBoot.ProjectSpringBoot.Entity.Role;
import com.SpringBoot.ProjectSpringBoot.Entity.User;
import com.SpringBoot.ProjectSpringBoot.Repository.UserRepository;
import com.SpringBoot.ProjectSpringBoot.Response.AuthResponse;
import com.SpringBoot.ProjectSpringBoot.Response.UserResponse;
import com.SpringBoot.ProjectSpringBoot.Security.JwtUtil;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserService {

	@Value("${ADMIN_SECRET}")
	private String adminSecret;
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final JwtUtil jwtUtil;
			
	
		public String register(RegisterDTO dto) {
			if(userRepository.existsByEmail(dto.getEmail())) {
				throw new RuntimeException("Email Already Exists...!");
			}
			User user=new User();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setPassword(encoder.encode(dto.getPassword()));
			if(dto.getAdminSecret()!=null && 
					dto.getAdminSecret().equals(adminSecret)) {
				user.setRole(Role.ADMIN);
			}
			else {
				user.setRole(Role.USER);
			}
			
			userRepository.save(user);
			
			return "User Register Successfully";
	
	}
		
		public AuthResponse login(LoginDTO loginDTO) {
			User user=userRepository.findByEmail(loginDTO.getEmail())
					.orElseThrow(()-> new RuntimeException("Invalid Email or Password"));
			
			if(!encoder.matches(loginDTO.getPassword(), user.getPassword())) {
				 throw new RuntimeException("Invalid Email or Password");
			}
			
			String token=jwtUtil.generateToken(user.getEmail(),user.getRole().name());
			
		UserResponse response=new UserResponse(
				user.getId(),
				user.getEmail(),
				user.getName(),
				user.getRole());
				
			return new AuthResponse(token, response);
		}		
		
		public List<UserResponse>getAllUsers(){
			return userRepository.findAll()
					.stream()
					.map(user-> new UserResponse(
							user.getId(),
							user.getEmail(),
							user.getName(),
							user.getRole()))
					.toList();
					
		}
		
}
