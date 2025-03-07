package com.ecommercegroup.gymecommerce.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MasterPasswordAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var login = authentication.getName();
		var password = (String) authentication.getCredentials();
		
		String masterLogin = "masterlogin";
		String masterPassword = "masterpassword";
		
		if(masterLogin.equals(login) && masterPassword.equals(password)) {
			return new UsernamePasswordAuthenticationToken("Master", null, List.of(new SimpleGrantedAuthority("ADMIN")));
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
