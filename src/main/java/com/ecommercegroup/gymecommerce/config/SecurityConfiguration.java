package com.ecommercegroup.gymecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
				//.formLogin(Customizer.withDefaults())
				//.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/user/register").permitAll();
					authorize.requestMatchers("/login").permitAll();
					authorize.requestMatchers("/users/me/**").hasAnyRole("USER", "EMPLOYEE", "ADMIN");
					authorize.requestMatchers("/alunos/**").hasAnyRole("EMPLOYEE", "ADMIN");
					authorize.requestMatchers("/admin").hasRole("ADMIN");
					authorize.anyRequest().authenticated();
				})
				.build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();	
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(10);
	}
	
	
	
}
