package com.ecommercegroup.gymecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercegroup.gymecommerce.dto.UserDto;
import com.ecommercegroup.gymecommerce.entities.User;
import com.ecommercegroup.gymecommerce.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gym")
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		User user = userService.save(userDto);
		return ResponseEntity.status(201).body(new UserDto(user));
	}
	
	@GetMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UserDto> getProfile(Authentication authentication) {
		String userCpf = authentication.getName();
		User user = userService.findByCpf(userCpf);
		return ResponseEntity.ok().body(new UserDto(user));
	}
	
	@PutMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto userDto, Authentication authentication) {
		String userCpf = authentication.getName();
		User user = userService.update(userCpf, userDto);
		return ResponseEntity.ok(new UserDto(user));
	}
	
	@DeleteMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> deleteProfile(Authentication authentication) {
		String userCpf = authentication.getName();
		User user = userService.findByCpf(userCpf);
		userService.deleteById(user.getId());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/alunos")
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<User>> findAll() {
		List<User> listUsers = userService.findAll();
		
		return ResponseEntity.ok().body(listUsers);
	}
	
}
