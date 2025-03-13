package com.managementgroup.gymmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managementgroup.gymmanagement.entities.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
