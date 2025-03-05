package com.ecommercegroup.gymecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/alunos")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto) {
		User newUser = userService.save(userDto);
		
		return ResponseEntity.status(201).body(new UserDto(newUser));
	}
	
	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/searchid/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id) {
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(new UserDto(user));
	}

	@GetMapping("/searchname/{name}")
	public ResponseEntity<List<UserDto>> findByName(@PathVariable String name) {
		List<User> listUsers = userService.findByName(name);
		List<UserDto> listDto = listUsers.stream().map(UserDto::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody UserDto userDto, @PathVariable Long id){
		User user = userService.fromDto(userDto);
		user.setId(id);
		user = userService.update(user);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		userService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
