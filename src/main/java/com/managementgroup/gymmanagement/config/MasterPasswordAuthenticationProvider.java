package com.managementgroup.gymmanagement.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MasterPasswordAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var username = authentication.getName();
		var password = (String) authentication.getCredentials();
		
		
		String masterLogin = "masterlogin";
		String masterPassword = "masterpassword";
		
		if(masterLogin.equals(username) && masterPassword.equals(password)) {
			return new UsernamePasswordAuthenticationToken("Master", null, List.of(new SimpleGrantedAuthority("ADMIN")));
		}
		
		// Se não for o usuário master, retorna null para que o DaoAuthenticationProvider seja chamado
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	
	public void setUserDetailsService(UserDetailsService userDetailsService) {	
		this.userDetailsService = userDetailsService;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
