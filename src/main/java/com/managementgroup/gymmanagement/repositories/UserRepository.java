package com.managementgroup.gymmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managementgroup.gymmanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);

	
	List<User> findByName(String name);

	Optional<User> findByCpf(String cpf);
}
