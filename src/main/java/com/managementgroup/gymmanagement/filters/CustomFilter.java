package com.managementgroup.gymmanagement.filters;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		// Permitir todos os endpoints /auth/** sem autenticação
		if (request.getRequestURI().startsWith("/auth/")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
		
		// Permitir endpoints específicos
		if (request.getRequestURI().equals("/gym/register") || 
			request.getRequestURI().equals("/login")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
		
		String secretHeader = request.getHeader("x-secret");
		
		if (secretHeader != null) {
			if (secretHeader.equals("secr3t")) {
				Authentication authentication = new UsernamePasswordAuthenticationToken("Secreto", null, List.of(new SimpleGrantedAuthority("ADMIN")));
				
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
				System.out.println("CustomFilter - Autenticação secreta aplicada");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
