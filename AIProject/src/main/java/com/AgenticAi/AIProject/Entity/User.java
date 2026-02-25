package com.AgenticAi.AIProject.Entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_Detail")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;
	
	
	private String password;
	private String displayName;
	
	
	private String profileImage;
	
	private String provider;     // LOCAL or GOOGLE
    private String providerId;   // Google "sub"
    
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ChatSession>chatSessions;
}
