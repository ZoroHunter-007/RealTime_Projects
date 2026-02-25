package com.SpringBoot.ProjectSpringBoot.Security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilter{


	private final JwtUtil jwtUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		
		String header=httpRequest.getHeader("Authorization");
		
		if(header!=null && header.startsWith("Bearer ")) {
			String token=header.substring(7);
			
			if(jwtUtil.validateToken(token)) {
				

				Claims claims=jwtUtil.extractAllClaims(token);
				String email=claims.getSubject();
				String role=claims.get("role", String.class);
				
				List<SimpleGrantedAuthority>autorities=List.of(new SimpleGrantedAuthority("ROLE_"+ role));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                               autorities
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		chain.doFilter(httpRequest, response);
		
	}
	
	
	
}
