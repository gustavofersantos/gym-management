package com.ecommercegroup.gymecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(authorize -> {
					authorize.anyRequest().authenticated();
				})
				.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	UserDetailsService userDetailService (PasswordEncoder encoder) {
		UserDetails user1 = User.builder()
				.username("usuario")
				.password(encoder.encode("123456"))
				.roles("USER")
				.build();
		
		UserDetails user2 = User.builder()
				.username("admin")
				.password(encoder.encode("admin"))
				.roles("ADMIN")
				.build();
				
		return new InMemoryUserDetailsManager(user1, user2);
		
	}
	
}
