package com.managementgroup.gymmanagement.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Tentando buscar usuário com CPF: " + username);
		
		User user = userRepository.findByCpf(username)
			.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com CPF: " + username));
		
		return user;
	}

}
