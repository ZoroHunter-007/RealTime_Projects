package com.SpringBoot.ProjectSpringBoot.Security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	private Key getSignKey() {
		 return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String email,String role) {

	    return Jwts.builder()
	            .subject(email)
	            .claim("role", role)
	            .issuedAt(new Date())
	            .expiration(new Date(System.currentTimeMillis() + expiration))
	            .signWith(getSignKey())
	            .compact();
	}
	
	
	public String extractEmail(String token) {
	    return Jwts.parser()
	            .verifyWith((javax.crypto.SecretKey) getSignKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}
	
	public boolean validateToken(String token) {
	    try {
	        Jwts.parser()
	                .verifyWith((javax.crypto.SecretKey) getSignKey())
	                .build()
	                .parseSignedClaims(token);
	        return true;
	    } catch (JwtException e) {
	        return false;
	    }
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) getSignKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
