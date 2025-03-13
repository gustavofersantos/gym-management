package com.managementgroup.gymmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managementgroup.gymmanagement.entities.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	
}
