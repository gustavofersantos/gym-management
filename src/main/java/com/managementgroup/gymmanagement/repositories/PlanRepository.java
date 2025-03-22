package com.managementgroup.gymmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managementgroup.gymmanagement.entities.Plan;
import com.managementgroup.gymmanagement.entities.User;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

	Optional<Plan> findByName(String planName);

}
