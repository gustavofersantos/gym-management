package com.managementgroup.gymmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.dto.UserDto;
import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.enums.Roles;
import com.managementgroup.gymmanagement.repositories.UserRepository;
import com.managementgroup.gymmanagement.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Transactional
	public User save(UserDto userDto, Roles role) {
		if (userRepository.existsByCpf(userDto.getCpf())) {
			throw new RuntimeException("Cpf já cadastrado!");
		} else if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new RuntimeException("E-mail já cadastrado!");
		} else if (userRepository.existsByPhone(userDto.getPhone())) {
			throw new RuntimeException("Telefone já cadastrado!");
		}
		
		User user = fromDto(userDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRole(role);
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Id de usuário não encontrado"));
	}
	
	public List<User> findByName(String userName) {
		List<User> listUsers = userRepository.findByName(userName);
		if (listUsers != null ) {
			return listUsers;
		}
		throw new ObjectNotFoundException("Nome de usuário não encontrado");
	}
	
	public User findByCpf(String cpf) {
	    return userRepository.findByCpf(cpf)
	        .orElseThrow(() -> new ObjectNotFoundException("Usuário com CPF " + cpf + " não encontrado"));
	}
	
	@Transactional
	public User update(String cpf, UserDto userDto) {
		User user = findByCpf(cpf);
		updateData(user, userDto);
		return userRepository.save(user);
	}
	
	private void updateData(User user, UserDto userDto) {
		user.setName(user.getName());
		user.setEmail(user.getEmail());	
	}

	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getPhone(), userDto.getEmail(), userDto.getBirthdate(), userDto.getCpf(), userDto.getPassword(), null);
	}
}
