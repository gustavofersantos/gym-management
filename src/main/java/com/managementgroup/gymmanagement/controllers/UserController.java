package com.managementgroup.gymmanagement.controllers;

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

import com.managementgroup.gymmanagement.dto.UserDto;
import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.entities.enums.Roles;
import com.managementgroup.gymmanagement.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gym")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		User user = userService.save(userDto, Roles.USER);
		return ResponseEntity.status(201).body(new UserDto(user));
	}

	@PostMapping("/register-employee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDto> createEmployee(@Valid @RequestBody UserDto userDto) {
		User employee = userService.save(userDto, Roles.EMPLOYEE);
		return ResponseEntity.status(201).body(new UserDto(employee));
	}

	@PostMapping("/register-manager")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDto> createAdmin(@Valid @RequestBody UserDto userDto) {
		User admin = userService.save(userDto, Roles.ADMIN);
		return ResponseEntity.status(201).body(new UserDto(admin));
	}

	@GetMapping("/alunos")
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<User>> findAll() {
		List<User> listUsers = userService.findAll();

		return ResponseEntity.ok().body(listUsers);
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

	@DeleteMapping("/delete-user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteUser(Long id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
