package com.managementgroup.gymmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.managementgroup.gymmanagement.repositories.PlanRepository;
import com.managementgroup.gymmanagement.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlanRepository planRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		planRepository.deleteAll();
		
	}
}
