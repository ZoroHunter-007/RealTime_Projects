package com.example.Agentic_AI.Entity;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_Detail")
public class User {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(unique = true)
	private String email;
	
	
	private String password;
	private String displayName;
	
}
