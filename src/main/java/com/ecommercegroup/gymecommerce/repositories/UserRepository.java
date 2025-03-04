package com.ecommercegroup.gymecommerce.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercegroup.gymecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<User> findByName(String name);

}
