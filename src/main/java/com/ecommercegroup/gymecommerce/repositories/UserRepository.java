package com.ecommercegroup.gymecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercegroup.gymecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);


	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<User> findByName(String name);

	Optional<User> findByCpf(String cpf);
}
