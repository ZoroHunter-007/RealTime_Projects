package com.SpringBoot.ProjectSpringBoot.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(name="username",nullable = false)
	private String name;
	
	@Column(name="email",nullable = false,unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Task> task;
}
